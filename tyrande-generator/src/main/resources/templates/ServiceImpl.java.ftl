package ${packageName}.service.${classNameLower};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packageName}.model.${classNameLower}.${className}Model;
import ${packageName}.vo.${classNameLower}.${className}Vo;
import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ${tableNameRemark} service 实现层
 *
 * @author ${author}
 * @date ${datetime}
 */
@Service("${classFirstNameLower}Service")
public class ${className}ServiceImpl extends ServiceImpl<${className}Dao, ${className}Model> implements ${className}Service {

    @Resource
    private ${className}Dao ${classFirstNameLower}Dao;

    /**
     * 分页查询
     */
    @Override
    public IPage<${className}Model> getPageList(${className}Vo ${classFirstNameLower}Vo) {
        QueryWrapper<${className}Model> wrapper = new QueryWrapper();
        IPage<${className}Model> page = ${classFirstNameLower}Dao.selectMapsPage(${classFirstNameLower}Vo, wrapper);
        return page;
    }
}