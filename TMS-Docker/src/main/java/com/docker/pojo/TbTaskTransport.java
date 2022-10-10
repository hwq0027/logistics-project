package com.docker.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 运输任务表
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Getter
@Setter
@TableName("tb_task_transport")
public class TbTaskTransport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private String id;

    /**
     * 车次id
     */
    @TableField("transport_trips_id")
    private String transportTripsId;

    /**
     * 起始机构id
     */
    @TableField("start_agency_id")
    private String startAgencyId;

    /**
     * 目的机构id
     */
    @TableField("end_agency_id")
    private String endAgencyId;

    /**
     * 任务状态，1为待执行（对应 未发车）、2为进行中（对应在途）、3为待确认（保留状态）、4为已完成（对应 已交付）、5为已取消
     */
    @TableField("status")
    private Integer status;

    /**
     * 任务分配状态(1未分配2已分配3待人工分配)
     */
    @TableField("assigned_status")
    private Integer assignedStatus;

    /**
     * 满载状态(1.半载2.满载3.空载)
     */
    @TableField("loading_status")
    private Integer loadingStatus;

    /**
     * 车辆id
     */
    @TableField("truck_id")
    private String truckId;

    /**
     * 提货凭证
     */
    @TableField("cargo_pick_up_picture")
    private String cargoPickUpPicture;

    /**
     * 货物照片
     */
    @TableField("cargo_picture")
    private String cargoPicture;

    /**
     * 运回单凭证
     */
    @TableField("transport_certificate")
    private String transportCertificate;

    /**
     * 交付货物照片
     */
    @TableField("deliver_picture")
    private String deliverPicture;

    /**
     * 提货纬度值
     */
    @TableField("delivery_latitude")
    private String deliveryLatitude;

    /**
     * 提货经度值
     */
    @TableField("delivery_longitude")
    private String deliveryLongitude;

    /**
     * 交付纬度值
     */
    @TableField("deliver_latitude")
    private String deliverLatitude;

    /**
     * 交付经度值
     */
    @TableField("deliver_longitude")
    private String deliverLongitude;

    /**
     * 计划发车时间
     */
    @TableField("plan_departure_time")
    private LocalDateTime planDepartureTime;

    /**
     * 实际发车时间
     */
    @TableField("actual_departure_time")
    private LocalDateTime actualDepartureTime;

    /**
     * 计划到达时间
     */
    @TableField("plan_arrival_time")
    private LocalDateTime planArrivalTime;

    /**
     * 实际到达时间
     */
    @TableField("actual_arrival_time")
    private LocalDateTime actualArrivalTime;

    /**
     * 计划提货时间
     */
    @TableField("plan_pick_up_goods_time")
    private LocalDateTime planPickUpGoodsTime;

    /**
     * 实际提货时间
     */
    @TableField("actual_pick_up_goods_time")
    private LocalDateTime actualPickUpGoodsTime;

    /**
     * 计划交付时间
     */
    @TableField("plan_delivery_time")
    private LocalDateTime planDeliveryTime;

    /**
     * 实际交付时间
     */
    @TableField("actual_delivery_time")
    private LocalDateTime actualDeliveryTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /*车辆一对一*//*根据表中的车辆id查询车牌号*/
    private PdTruck pdTruck;

    /*根据车辆id,和车次id,去车次与车辆和司机关联表中:查询司机id,*/
    private PdTransportTripsTruckDriver pdTransportTripsTruckDriver;





}
