package cn.hwq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("tb_inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("inventory")
    private Integer inventory;

    @TableField("begin_time")
    private LocalDateTime beginTime;

    @TableField("end_time")
    private LocalDateTime endTime;


}
