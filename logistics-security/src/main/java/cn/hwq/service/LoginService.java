package cn.hwq.service;

import cn.hwq.pojo.Customer;
import cn.hwq.vo.Result;

public interface LoginService {

   Result login(Customer customer);
}
