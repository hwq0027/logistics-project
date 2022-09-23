package cn.hwq.service.impl;

import cn.hwq.jwt.JwtUtil;
import cn.hwq.pojo.Customer;
import cn.hwq.security.LoginUser;
import cn.hwq.service.LoginService;
import cn.hwq.vo.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // JSON序列化工具
    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public Result login(Customer customer) {
        //通过注入的身份验证管理器来手动调用来认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customer.getAccount(),customer.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //使用authenticate拿到认证信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getCustomer().getId().toString();
//        //把完整信息存入redis中
//        try {
//            stringRedisTemplate.opsForValue().set("Login_user:"+userId,mapper.writeValueAsString(loginUser));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        //把token响应给前端
        String jwt = JwtUtil.createJWT(userId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        map.put("nickname",loginUser.getCustomer().getNickname());
        map.put("portrait",loginUser.getCustomer().getPortrait());
        map.put("id",loginUser.getCustomer().getId());
        return Result.ok(map);
    }
}
