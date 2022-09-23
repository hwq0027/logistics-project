package cn.hwq.order.service;

import cn.hwq.pojo.Order;
import cn.hwq.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hwq
 * @since 2022-09-16
 */
public interface OrderService extends IService<Order> {

    Result SaveOrderService(Order order);

}
