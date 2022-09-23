package cn.hwq.service.impl;

import cn.hwq.mapper.CustomerMapper;
import cn.hwq.pojo.Customer;
import cn.hwq.security.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired(required = false)
    private CustomerMapper customerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查询用户信息
        QueryWrapper<Customer> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",username);
        Customer customer = customerMapper.selectOne(queryWrapper);
        //如果查询不到数据就通过抛出异常来给出提示,在此处抛出的异常都会被另外一个过滤器捕获
        if(Objects.isNull(customer)){
            throw new RuntimeException("用户名或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中

        //封装成UserDetails对象返回
        return new LoginUser(customer);
    }
}