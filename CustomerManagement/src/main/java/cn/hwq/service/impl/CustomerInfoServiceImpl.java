package cn.hwq.service.impl;

import cn.hwq.mapper.CustomerInfoMapper;
import cn.hwq.pojo.CustomerInfo;
import cn.hwq.service.CustomerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hwq
 * @since 2022-09-13
 */
@Service
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements CustomerInfoService {

}
