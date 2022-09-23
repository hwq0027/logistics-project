package cn.hwq.service.impl;

import cn.hwq.mapper.CustomerMapper;
import cn.hwq.pojo.Customer;
import cn.hwq.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hwq
 * @since 2022-09-13
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /** JSON序列化工具*/
    private static final ObjectMapper mapper = new ObjectMapper();


//    RedisIdWorker redisIdWorker=new RedisIdWorker(stringRedisTemplate);
    @Override
    public boolean putCustomer(Customer customer) {
        System.out.println(stringRedisTemplate+"asdf");

        return save(customer);
    }
}
