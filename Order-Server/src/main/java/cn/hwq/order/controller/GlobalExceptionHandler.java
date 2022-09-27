package cn.hwq.order.controller;

import cn.hutool.json.JSONException;
import cn.hwq.vo.Result;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

//    //捕获指定异常
//    @ExceptionHandler(ListenerExecutionFailedException.class)
//    public ModelAndView handleException(ListenerExecutionFailedException e){
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject("errorMessage", "参数不符合规范!");
//        return modelAndView;
//    }

    //捕获指定异常
    @ExceptionHandler(ListenerExecutionFailedException.class)
    public void handleException(ListenerExecutionFailedException e){
        System.out.println("触发重试机制");
    }

}