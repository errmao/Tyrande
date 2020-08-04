package org.tyrande.system.model.sysdictvalue;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统字典值表对象 sys_dict_value
 *
 * @author Tyrande
 * @date 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict_value")
public class SysDictValueModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典项编号
     */
    private Long dictKeyId;
    /**
     * 展示值
     */
    private String dictValue;
    /**
     * 真实值
     */
    private String dictRealValue;
    /**
     * 字典顺序
     */
    private Integer dictOrder;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}