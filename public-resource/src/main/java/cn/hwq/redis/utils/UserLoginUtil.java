package cn.hwq.redis.utils;

import cn.hwq.pojo.Customer;

public class UserLoginUtil {

    private static final ThreadLocal<Customer> threadLocal=new ThreadLocal<>();

    public static void saveUser(Customer user){
        threadLocal.set(user);
    }

    public static Customer getUser(){
        return threadLocal.get();
    }

    public static void removeUser(){
        threadLocal.remove();
    }
}
