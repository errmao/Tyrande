package org.tyrande.system.dao.sysdictvalue;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tyrande.system.model.sysdictvalue.SysDictValueModel;

import java.util.List;

/**
 * 系统字典值表 dao 层
 *
 * @author Tyrande
 * @date 2020-08-04
 */
@Mapper
public interface SysDictValueDao extends BaseMapper<SysDictValueModel> {

    /**
     * 根据字典项查询字典值列表
     */
    List<SysDictValueModel> getByDictKey(@Param("dictKey") String dictKey);
}