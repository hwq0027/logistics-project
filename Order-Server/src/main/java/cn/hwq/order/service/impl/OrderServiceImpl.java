package cn.hwq.order.service.impl;


import cn.hwq.order.mapper.OrderMapper;
import cn.hwq.order.service.OrderService;
import cn.hwq.order.utils.RedisIdWorker;
import cn.hwq.pojo.Inventory;
import cn.hwq.pojo.Order;

import cn.hwq.vo.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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

    @Override
    public Result SaveOrderService(Order order) {
        // 1.查询优惠券
        Inventory inventory = inventoryService.getById(order.getVolume_type());
        // 2.判断秒杀是否开始
        if (inventory.getBeginTime().isAfter(LocalDateTime.now())) {
            // 尚未开始
            return Result.fail("秒杀尚未开始！");
        }
        // 3.判断秒杀是否已经结束
        if (inventory.getEndTime().isBefore(LocalDateTime.now())) {
            // 尚未开始
            return Result.fail("秒杀已经结束！");
        }
        // 4.判断库存是否充足
        if (inventory.getInventory() < 1) {
            // 库存不足
            return Result.fail("库存不足！");
        }
        //Long userId = UserHolder.getUser().getId();
        //创建锁对象 这个代码不用了，因为我们现在要使用分布式锁
        //SimpleRedisLock lock = new SimpleRedisLock("order:" + userId, stringRedisTemplate);
        RLock lock = redissonClient.getLock("lock:order:" + "hwq");
        //获取锁对象
        boolean isLock = lock.tryLock();

        //加锁失败
        if (!isLock) {
            return Result.fail("不允许重复下单");
        }
        try {
            //获取代理对象(事务)
            OrderServiceImpl proxy = (OrderServiceImpl) AopContext.currentProxy();
            return proxy.createVoucherOrder(order);
        } finally {
            //释放锁
            lock.unlock();
        }
    }


    @Transactional
    public Result createVoucherOrder(Order order) {
        // 6.扣减库存
        boolean success = inventoryService.update()
                .setSql("inventory = inventory - 1") // set stock = stock - 1
                .eq("id", order.getSType()).gt("inventory", 0) // where id = ? and stock > 0
                .update();
        if (!success) {
            // 扣减失败
            return Result.fail("库存不足！");
        }
        // 7.创建订单
        // 7.1.订单Order
        long orderId = redisIdWorker.nextId("order");
        order.setId(orderId);
        save(order);
        // 7.返回订单id
        return Result.ok(orderId);
    }
}


