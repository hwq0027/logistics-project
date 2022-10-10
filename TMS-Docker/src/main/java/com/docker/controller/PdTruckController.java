package com.docker.controller;


import com.docker.pojo.Page;
import com.docker.service.PdAuthUserService;
import com.docker.service.TbTaskTransportService;
import com.docker.service.impl.PdAuthUserServiceImpl;
import com.docker.service.impl.TbTaskTransportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 车辆信息表 前端控制器
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/pd-truck")
public class PdTruckController {
    /*运输任务管理*/
    @Autowired
    TbTaskTransportService service;

    /*查询运输任务*/
    @RequestMapping("/Transportation/query")
    public Page Query(@RequestBody Map<String,String> object){

        System.out.println(object);
        return service.Query(object);
    }

}
