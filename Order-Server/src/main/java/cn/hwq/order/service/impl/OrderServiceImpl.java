package cn.hwq.order.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hwq.order.mapper.OrderMapper;
import cn.hwq.order.service.OrderService;
import cn.hwq.order.utils.RedisIdWorker;
import cn.hwq.pojo.Inventory;
import cn.hwq.pojo.Order;

import cn.hwq.utils.BaiDuResult;
import cn.hwq.utils.UserHolder;
import cn.hwq.vo.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hwq
 * @since 2022-09-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    InventoryServiceImpl inventoryService;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    RedisIdWorker redisIdWorker;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private static final String ak = "64Ut0Peo2Dsb1l43FRl1nReM0tBdpE3L";

    @Override
    public Result SaveOrderService(Order order) {
        //1.基本校验
        if (order==null){
            return Result.fail("订单无效！");
        }
        //2.校验订单配送位置信息
        order = checkLocation(order);
        if (order.getStatus()==-1){
            return Result.fail("订单配送起始位置信息有误！");
        }
        //3.创建锁对象 使用分布式锁
        RLock lock = redissonClient.getLock("lock:order:" + "hwq");
        //4.获取锁对象
        boolean isLock = lock.tryLock();
        //5.加锁失败
        if (!isLock) {
            return Result.fail("当前活动火爆，请重试");
        }

        //6.查询优惠券
        Inventory inventory = inventoryService.getById(order.getVolumeType());
        System.out.println(LocalDateTime.now());
        //7.判断秒杀是否开始
        if (inventory.getBeginTime().isAfter(LocalDateTime.now())) {
            // 尚未开始
            return Result.fail("秒杀尚未开始！");
        }
        //8.判断秒杀是否已经结束
        if (inventory.getEndTime().isBefore(LocalDateTime.now())) {
            // 尚未开始
            return Result.fail("秒杀已经结束！");
        }
        //9.判断库存是否充足
        if (inventory.getInventory() < 1) {
            // 库存不足
            return Result.fail("库存不足！");
        }
        //10.取出下单客户id并保存
        String UserId = String.valueOf(UserHolder.getObject());
        order.setMemberId(UserId);


        //11.发送下单消息并释放锁
        try {
            // 交换机名称
            String exchangeName = "order.Exchange";
            // 消息
            String jsonStr = JSONUtil.toJsonStr(order);
            // 发送消息
            rabbitTemplate.convertAndSend(exchangeName, "ls.order", jsonStr);
        }finally {
            //释放锁
            lock.unlock();
        }
        //12.结果响应
        return Result.ok("抢单成功!");
    }

    @Transactional
    public Result createVoucherOrder(Order order) {
        //1.初始化订单信息
        order = initOderObject(order);
        // 2..扣减库存
        boolean success = inventoryService.update()
                .setSql("inventory = inventory - 1") // set stock = stock - 1
                .eq("id", order.getVolumeType()).gt("inventory", 0) // where id = ? and stock > 0
                .update();
        if (!success) {
            // 扣减失败
            return Result.fail("库存不足！");
        }
        // 7.创建订单
        save(order);
        // 8.返回订单id
        return Result.ok();
    }

    @Transactional
    public Order initOderObject(Order order){
        //初始化id
        order.setId(String.valueOf(redisIdWorker.nextId("order")));
        //初始化订单类型 1为同城订单，2为城际订单
        if(order.getSenderCityId().equals(order.getReceiverCityId())){
            order.setOrderType(1);
        }else {
            order.setOrderType(2);
        }
        //创建时间
        LocalDate now = LocalDate.now();
        order.setCreateTime(now.atTime(LocalTime.now()));

        //订单当前所属网点 currentAgencyId
        order.setCurrentAgencyId("未分配网点");
        //付款方式,1.预结2到付 paymentMethod
        order.setPaymentMethod(1);
        //预计到达时间estimatedArrivalTime
        order.setEstimatedArrivalTime(now.plusDays(3).atTime(LocalTime.now()));
        //距离，单位：公里distance
        String distance = countDistance(order.getSenderAddressId(), order.getReceiverAddressId());
        order.setDistance(distance);

        return order;
    }

    //计算两个地理位置的距离
    private String countDistance(String location1,String location2) {
        String url="https://api.map.baidu.com/routematrix/v2/driving?output=json&origins={}&destinations={}&ak={}";  //GET请求
        url = StrUtil.format(url,location1,location2, ak);

        String body = HttpRequest.get(url).execute().body();
        HashMap map = JSONUtil.toBean(body, HashMap.class);
        String status = String.valueOf(map.get("result"));

        String substring = status.substring(1,status.length());
        String substring1 = substring.substring(0,substring.length()-1);
        JSONObject jsonObject1 = JSONUtil.parseObj(substring1);
        String text = jsonObject1.getStr("distance");
        HashMap map1 = JSONUtil.toBean(text, HashMap.class);
        return String.valueOf(map1.get("text"));
    }


    //根据具体位置,县区,城市获取位置
    public BaiDuResult getBaiduResult(String address,String county,String city){
        JSONObject jsonObject1 = null;
        try {
            String url ="https://api.map.baidu.com/place/v2/search?query={}&tag={}&region={}&output=json&ak={}";
            url = StrUtil.format(url,address,county,city,ak);

            //发起get请求
            String body = HttpRequest.get(url).execute().body();
            JSONObject jsonObject = JSONUtil.parseObj(body);
            String results = jsonObject.getStr("results");
            JSONArray array = JSONUtil.parseArray(results);
            List<BaiDuResult> userList = JSONUtil.toList(array, BaiDuResult.class);
            if (userList.size()>0){
                return  userList.get(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    //校验并初始化订单配送信息
    public Order checkLocation(Order order){
        //校验订单起始位置
        BaiDuResult baiduResult = getBaiduResult(order.getSenderAddress(), order.getSenderCountyId(), order.getSenderCityId());
        BaiDuResult baiduResult2 = getBaiduResult(order.getReceiverAddress(), order.getReceiverCountyId(), order.getReceiverCityId());

        if(baiduResult==null||baiduResult2==null){
            System.out.println("信息有误");
            order.setStatus(-1);
            return order;
        }
        HashMap<String, String> location = baiduResult.getLocation();
        String lng = String.valueOf(location.get("lng"));
        String lat = String.valueOf(location.get("lat"));
        order.setSenderAddressId(lat+","+lng);

        HashMap<String, String> locatio2 = baiduResult2.getLocation();
        String lng2 = String.valueOf(locatio2.get("lng"));
        String lat2 = String.valueOf(locatio2.get("lat"));
        order.setReceiverAddressId(lat2+","+lng2);
        order.setStatus(0);
        return order;
    }

}


