package ${packageName}.dao.${classNameLower};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packageName}.model.${classNameLower}.${className}Model;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${tableNameRemark} dao层
 *
 * @author ${author}
 * @date ${datetime}
 */
@Mapper
public interface ${className}Dao extends BaseMapper<${className}Model> {

}