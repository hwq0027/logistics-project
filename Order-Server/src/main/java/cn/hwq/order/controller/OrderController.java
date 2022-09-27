package cn.hwq.order.controller;


import cn.hwq.order.service.impl.OrderServiceImpl;
import cn.hwq.pojo.Order;
import cn.hwq.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hwq
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderServiceImpl OrderServiceImpl;

    @RequestMapping("/SaveOrderService")
    public Result SaveOrderService(@RequestBody Order order){
        System.out.println("卧槽我进来了!!");

        return OrderServiceImpl.SaveOrderService(order);
    }

    @RequestMapping("/findAllOrder")
    public Result findAllOrder(){

        return Result.ok(OrderServiceImpl.list());
    }

}
