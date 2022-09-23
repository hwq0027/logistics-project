package cn.hwq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-09-22
 */
@Getter
@Setter
@TableName("tb_tms_menu")
@ToString
public class TmsMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("menu_name")
    private String menuName;

    @TableField("menu_superior")
    private String menuSuperior;

    @TableField("menu_status")
    private String menuStatus;

    @TableField("menu_level")
    private String menuLevel;

    @TableField("menu_icon")
    private String menuIcon;

    @TableField("router_path")
    private String routerPath;

    @TableField("menu_type")
    private String menuType;


}
