package cn.hwq.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hwq
 * @since 2022-09-16
 */
@Getter
@Setter
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("account")
    private String account;

    @TableField("s_name")
    private String sName;

    @TableField("s_phone")
    private String sPhone;

    @TableField("s_type")
    private String sType;

    @TableField("s_remark")
    private String sRemark;

    @TableField("s_address")
    private String sAddress;

    @TableField("s_location")
    private String sLocation;

    @TableField("r_name")
    private String rName;

    @TableField("r_phone")
    private String rPhone;

    @TableField("r_company")
    private String rCompany;

    @TableField("r_remark")
    private String rRemark;

    @TableField("r_address")
    private String rAddress;

    @TableField("r_location")
    private String rLocation;

    @TableField("price")
    private String price;

    @TableField("inventory")
    private String inventory;


    @TableField("volume_type")
    private int volume_type;

    @TableField("createtime")
    private LocalDateTime createtime;



    @TableField("updatetime")
    private LocalDateTime updatetime;


}
