package com.docker.mapper;

import com.docker.pojo.PdTruck;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 车辆信息表 Mapper 接口
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Mapper
public interface PdTruckMapper extends BaseMapper<PdTruck> {

    @Select("select * from ls_db_base.pd_truck p where 1=1 and p.id=${id}")
    /*根据车辆id查询信息*/
    PdTruck selectBytid(int id);

}
