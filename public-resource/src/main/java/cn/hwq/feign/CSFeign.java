package cn.hwq.feign;

import cn.hwq.pojo.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "userservice")
public interface CSFeign {

    @GetMapping("/SM/getAllSMList")
    List<Customer> getAllSMList();
}
