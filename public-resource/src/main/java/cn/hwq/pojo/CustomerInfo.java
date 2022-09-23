package cn.hwq.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author hwq
 * @since 2022-09-13
 */
@Getter
@Setter
@TableName("tb_customer_info")
@ToString
public class CustomerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @TableId("account")
    private Long account;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 位置
     */
    @TableField("location")
    private String location;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 头像存储地址
     */
    @TableField("head_portrait")
    private String headPortrait;


}
