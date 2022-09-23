package cn.hwq.service;

import cn.hwq.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

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
