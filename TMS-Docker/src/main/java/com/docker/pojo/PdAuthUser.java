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
 * 
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Getter
@Setter
@TableName("pd_auth_user")
public class PdAuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("id")
    private Long id;

    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 组织ID
#c_core_org
     */
    @TableField("org_id")
    private Long orgId;

    /**
     * 岗位ID
#c_core_station
     */
    @TableField("station_id")
    private Long stationId;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 性别
#Sex{W:女;M:男;N:未知}
     */
    @TableField("sex")
    private String sex;

    /**
     * 启用状态 1启用 0禁用
     */
    @TableField("status")
    private Boolean status;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 工作描述	比如：  市长、管理员、局长等等   用于登陆展示
     */
    @TableField("work_describe")
    private String workDescribe;

    /**
     * 最后一次输错密码时间
     */
    @TableField("password_error_last_time")
    private LocalDateTime passwordErrorLastTime;

    /**
     * 密码错误次数
     */
    @TableField("password_error_num")
    private Integer passwordErrorNum;

    /**
     * 密码过期时间
     */
    @TableField("password_expire_time")
    private LocalDateTime passwordExpireTime;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 创建人id
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    @TableField("update_user")
    private Long updateUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
