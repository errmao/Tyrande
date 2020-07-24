package ${packageName}.model.${classNameLower};

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* ${tableNameRemark}对象 ${tableName}
*
* @author ${author}
* @date ${datetime}
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("${tableName}")
public class ${className}Model implements Serializable {

private static final long serialVersionUID = 1L;

/**
* 主键
*/
@TableId(value = "id", type = IdType.ASSIGN_ID)
private Intege id;

<#list columns as column>
    /**
    * ${column.columnComment}
    */
    private ${column.javaType} ${column.javaField};
</#list>

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