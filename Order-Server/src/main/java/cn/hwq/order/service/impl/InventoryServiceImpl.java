package cn.hwq.order.service.impl;


import cn.hwq.order.mapper.InventoryMapper;
import cn.hwq.order.service.InventoryService;
import cn.hwq.pojo.Inventory;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hwq
 * @since 2022-09-16
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

}
