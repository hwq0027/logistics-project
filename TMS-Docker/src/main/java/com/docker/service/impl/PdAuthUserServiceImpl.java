package com.docker.service.impl;

import com.docker.mapper.PdTransportTripsTruckDriverMapper;
import com.docker.pojo.Page;
import com.docker.pojo.PdAuthUser;
import com.docker.mapper.PdAuthUserMapper;
import com.docker.pojo.PdTransportTripsTruckDriver;
import com.docker.pojo.TbTaskTransport;
import com.docker.service.PdAuthUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Service
public class PdAuthUserServiceImpl extends ServiceImpl<PdAuthUserMapper, PdAuthUser> implements PdAuthUserService {



}
