package cn.hwq.service;

import cn.hwq.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hwq
 * @since 2022-09-13
 */
public interface CustomerService extends IService<Customer> {
    boolean putCustomer(Customer customer);

    //List<Customer> getAllSMList();



}
