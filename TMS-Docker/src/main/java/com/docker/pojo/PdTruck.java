package com.docker.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 车辆信息表
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Getter
@Setter
@TableName("pd_truck")
public class PdTruck implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private String id;

    /**
     * 车辆类型id
     */
    @TableField("truck_type_id")
    private String truckTypeId;

    /**
     * 所属车队id
     */
    @TableField("fleet_id")
    private String fleetId;

    /**
     * 品牌
     */
    @TableField("brand")
    private String brand;

    /**
     * 车牌号码
     */
    @TableField("license_plate")
    private String licensePlate;

    /**
     * GPS设备id
     */
    @TableField("device_gps_id")
    private String deviceGpsId;

    /**
     * 准载重量
     */
    @TableField("allowable_load")
    private BigDecimal allowableLoad;

    /**
     * 准载体积
     */
    @TableField("allowable_volume")
    private BigDecimal allowableVolume;

    /**
     * 车辆行驶证信息id
     */
    @TableField("truck_license_id")
    private String truckLicenseId;

    /**
     * 状态 0：禁用 1：正常
     */
    @TableField("status")
    private Integer status;




}
