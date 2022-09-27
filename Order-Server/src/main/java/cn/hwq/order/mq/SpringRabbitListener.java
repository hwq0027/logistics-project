package cn.hwq.order.mq;

import cn.hutool.json.JSONUtil;
import cn.hwq.order.service.OrderService;
import cn.hwq.order.service.impl.OrderServiceImpl;
import cn.hwq.pojo.Order;
import cn.hwq.vo.Result;
import javafx.scene.shape.Mesh;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Component
@Slf4j
public class SpringRabbitListener {

    @Autowired
    OrderServiceImpl orderService;


    @RabbitListener(queues = "order.queue")
    public void listenOrderQueue1(String msg){
        //将订单转换为对象
        Order order = JSONUtil.toBean(msg, Order.class);
        //初始化并创建订单
        //订单状态status
        order.setStatus(0);
        orderService.createVoucherOrder(order);
    }


    //死信队列，将订单状态设置为-1，后续进行人工补偿
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "dl.queue"),
            exchange = @Exchange(name = "dl.Exchange", type = ExchangeTypes.DIRECT),
            key = "simple"
    ))
    public void listenDLQueue(String msg){
        try {
            //将订单转换为对象
            Order order = JSONUtil.toBean(msg, Order.class);
            //订单状态status
            order.setStatus(-1);
            orderService.createVoucherOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(msg+"系统异常！！！");
        }
    }




}