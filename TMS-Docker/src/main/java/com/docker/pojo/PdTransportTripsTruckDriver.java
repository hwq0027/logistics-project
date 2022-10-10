package com.docker.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 车次与车辆和司机关联表
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Getter
@Setter
@TableName("pd_transport_trips_truck_driver")
public class PdTransportTripsTruckDriver implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private String id;

    /**
     * 车辆id
     */
    @TableField("truck_id")
    private String truckId;

    /**
     * 车次id
     */
    @TableField("transport_trips_id")
    private String transportTripsId;

    /**
     * 司机id
     */
    @TableField("user_id")
    private String userId;

    /*根据司机id查询司机*/
    private PdAuthUser user;


}
