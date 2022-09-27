package cn.hwq.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author hwq
 * @since 2022-09-24
 */
@Getter
@Setter
@TableName("pd_order")
@ToString
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private String id;

    /**
     * 订单类型，1为同城订单，2为城际订单
     */
    @TableField("order_type")
    private Integer orderType;

    /**
     * 取件类型，1为网点自寄，2为上门取件
     */
    @TableField("pickup_type")
    private Integer pickupType;

    /**
     * 下单时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 客户id
     */
    @TableField("member_id")
    private String memberId;

    /**
     * 收件人省份id
     */
    @TableField("receiver_province_id")
    private String receiverProvinceId;

    /**
     * 收件人城市id
     */
    @TableField("receiver_city_id")
    private String receiverCityId;

    /**
     * 收件人区县id
     */
    @TableField("receiver_county_id")
    private String receiverCountyId;

    /**
     * 收件人详细地址
     */
    @TableField("receiver_address")
    private String receiverAddress;

    /**
     * 收件人地址id
     */
    @TableField("receiver_address_id")
    private String receiverAddressId;

    /**
     * 收件人姓名
     */
    @TableField("receiver_name")
    private String receiverName;

    /**
     * 收件人电话
     */
    @TableField("receiver_phone")
    private String receiverPhone;

    /**
     * 发件人省份id
     */
    @TableField("sender_province_id")
    private String senderProvinceId;

    /**
     * 发件人城市id
     */
    @TableField("sender_city_id")
    private String senderCityId;

    /**
     * 发件人区县id
     */
    @TableField("sender_county_id")
    private String senderCountyId;

    /**
     * 发件人详细地址
     */
    @TableField("sender_address")
    private String senderAddress;

    /**
     * 发件人地址id
     */
    @TableField("sender_address_id")
    private String senderAddressId;

    /**
     * 发件人姓名
     */
    @TableField("sender_name")
    private String senderName;

    /**
     * 发件人电话
     */
    @TableField("sender_phone")
    private String senderPhone;

    /**
     * 订单当前所属网点
     */
    @TableField("current_agency_id")
    private String currentAgencyId;

    /**
     * 付款方式,1.预结2到付
     */
    @TableField("payment_method")
    private Integer paymentMethod;

    /**
     * 付款状态,1.未付2已付
     */
    @TableField("payment_status")
    private Integer paymentStatus;

    /**
     * 金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 预计到达时间
     */
    @TableField("estimated_arrival_time")
    private LocalDateTime estimatedArrivalTime;

    /**
     * 距离，单位：公里
     */
    @TableField("distance")
    private String distance;

    /**
     * 订单状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 优惠券类型
     */
    @TableField("volume_type")
    private Integer volumeType;


}
