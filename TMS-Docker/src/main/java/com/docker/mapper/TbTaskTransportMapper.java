package com.docker.mapper;

import com.docker.pojo.PdTransportTripsTruckDriver;
import com.docker.pojo.TbTaskTransport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运输任务表 Mapper 接口
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Mapper
public interface TbTaskTransportMapper extends BaseMapper<TbTaskTransport> {
/*
    @Results(id="stuMap",value = {
            @Result(property = "id",column = "Id"),
            @Result(property = "name",column="transport_trips_id"),
            @Result(property = "age",column = "Age"),
            @Result(property = "classId",column = "ClassID"),
            @Result(property = "studentClass",column = "ClassID",one = @One(select = "com.lyb.springmybatisdemo.mapper.StudentClassMapper.selectById"))
    })


    @Select("  select * from ls_db_user.tb_task_transport a \n" +
            "  inner join ls_db_base .pd_truck  truck t1 on truck.id=a.truck_id\n" +
            "  inner join  ls_db_base.pd_transport_trips_truck_driver d on d.truck_id=a.truck_id\n" +
            "and d.transport_trips_id=a.transport_trips_id  \n" +
            "inner join ls_db_user.`pd_auth_user` p on p.`id`=d.user_id\n" +
            "\n" +
            "where 1=1 and  a.`id` like  '%' ${map.id} '%'  and p.name like '%' ${map.name} '%' and a.`status`=${map.status}")
    @ResultMap(value = "stuMap")*/
    List<TbTaskTransport> Query(@Param("map") Map<String,String> objectMap);

/*
    List<TbTaskTransport> Query(Map<String,String> objectMap);*/

}
