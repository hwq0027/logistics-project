package com.docker.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.docker.pojo.PdTransportTripsTruckDriver;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.docker.pojo.TbTaskTransport;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车次与车辆和司机关联表 Mapper 接口
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Mapper
public interface PdTransportTripsTruckDriverMapper extends BaseMapper<PdTransportTripsTruckDriver> {

    @Results({
            @Result(property = "id",column = "Id"),
            @Result(property = "truckId",column="truck_id"),
            @Result(property = "transportTripsId",column = "transport_trips_id"),
            @Result(property = "userId",column = "user_id"),
            /*根据司机id查询司机_姓名*/
            @Result(property = "user",column = "user_id",one = @One(select = "com.docker.mapper.PdAuthUserMapper.selectById"))
    })

    /*根据车辆id,和车次id,去车次与车辆和司机关联表中:查询司机id,*/
    @Select("select * from ls_db_base.pd_transport_trips_truck_driver d" +
            "where 1=1 and d.truck_id=${truck_id}\n" +
            "and d.transport_trips_id=${transport_trips_id}")
     List<PdTransportTripsTruckDriver> findtruckdirve(int  truck_id,int transport_trips_id);



}
