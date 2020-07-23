package ${packageName}.model.${moduleName};

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
public class ${ClassName}Model implements Serializable {

private static final long serialVersionUID = 1L;

@TableId(value = "id", type = IdType.ASSIGN_ID)
private Intege id;

#foreach ($column in $columns)
/** $column.columnComment */
private $column.javaType $column.javaField;
#end

@TableField(fill = FieldFill.INSERT)
private Date createTime;

@TableField(fill = FieldFill.INSERT_UPDATE)
private Date updateTime;

}