package com.docker.service.impl;

import com.docker.pojo.PdTruck;
import com.docker.mapper.PdTruckMapper;
import com.docker.service.PdTruckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车辆信息表 服务实现类
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Service
public class PdTruckServiceImpl extends ServiceImpl<PdTruckMapper, PdTruck> implements PdTruckService {

}
