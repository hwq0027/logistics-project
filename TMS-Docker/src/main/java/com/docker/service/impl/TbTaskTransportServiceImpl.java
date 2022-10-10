package com.docker.service.impl;

import com.docker.pojo.Page;
import com.docker.pojo.TbTaskTransport;
import com.docker.mapper.TbTaskTransportMapper;
import com.docker.service.TbTaskTransportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运输任务表 服务实现类
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Service
public class TbTaskTransportServiceImpl extends ServiceImpl<TbTaskTransportMapper, TbTaskTransport> implements TbTaskTransportService {
     @Autowired
     TbTaskTransportMapper mapper;
    public Page Query(Map<String, String> objectMap) {
        PageHelper.startPage(Integer.valueOf(objectMap.get("pageNum")),Integer.valueOf(objectMap.get("pageSize")));
        List<TbTaskTransport> query = mapper.Query(objectMap);

        PageInfo<TbTaskTransport> pageInfo = new PageInfo<>(query);

        Page<TbTaskTransport> page = new Page<>();
        page.setRows(query);
        page.setTotal(pageInfo.getTotal());
        return page;
    }
}
