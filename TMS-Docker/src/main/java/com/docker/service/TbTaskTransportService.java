package com.docker.service;

import com.docker.pojo.Page;
import com.docker.pojo.TbTaskTransport;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 运输任务表 服务类
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
public interface TbTaskTransportService extends IService<TbTaskTransport> {
    /*查询所有*/
    Page Query(Map<String,String> objectMap);
}
