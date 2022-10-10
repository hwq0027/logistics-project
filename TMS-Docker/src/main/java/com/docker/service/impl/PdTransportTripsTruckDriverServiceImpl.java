package com.docker.service.impl;

import com.docker.pojo.PdTransportTripsTruckDriver;
import com.docker.mapper.PdTransportTripsTruckDriverMapper;
import com.docker.service.PdTransportTripsTruckDriverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车次与车辆和司机关联表 服务实现类
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Service
public class PdTransportTripsTruckDriverServiceImpl extends ServiceImpl<PdTransportTripsTruckDriverMapper, PdTransportTripsTruckDriver> implements PdTransportTripsTruckDriverService {

}
