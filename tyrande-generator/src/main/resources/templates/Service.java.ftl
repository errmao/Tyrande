package ${packageName}.service.${classNameLower};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName}.model.${classNameLower}.${className}Model;
import ${packageName}.vo.${classNameLower}.${className}Vo;
import java.util.List;

/**
 * ${tableNameRemark} service层
 *
 * @author ${author}
 * @date ${datetime}
 */
public interface ${className}Service extends IService<${className}Model> {

    /**
     * 分页查询
     */
    IPage<${className}Model> getPageList(${className}Vo ${classFirstNameLower}Vo);
}