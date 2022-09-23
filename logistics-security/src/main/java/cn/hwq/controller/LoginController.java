package cn.hwq.controller;


import cn.hwq.pojo.Customer;
import cn.hwq.service.LoginService;
import cn.hwq.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/user/login")
    public Result login(@RequestBody Customer customer){
        System.out.println(customer);
        return loginService.login(customer);
    }
}
