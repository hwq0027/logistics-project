package cn.hwq.controller;

import cn.hwq.pojo.Customer;
import cn.hwq.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HWQ
 */
@Controller
@RequestMapping(("/SM"))
public class SMController {

    @Autowired
    CustomerService customerService;

    @PutMapping("/addCustomer")
    @ResponseBody
    public boolean addCustomer(@RequestBody Customer customer){
        return customerService.putCustomer(customer);

    }

    @PostMapping("/getAllSMList")
    @ResponseBody
    public List<Customer> getAllSMList(){
        return customerService.list();
    }

}
