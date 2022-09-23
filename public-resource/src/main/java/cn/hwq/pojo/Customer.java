package cn.hwq.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.swing.*;

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
@TableName("tb_customer")
@ToString
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    @TableField("role")
    private String role;

    @TableField("nickname")
    private String nickname;

    @TableField("portrait")
    private String portrait;

    @TableField(exist = false)
    private String token;
}
