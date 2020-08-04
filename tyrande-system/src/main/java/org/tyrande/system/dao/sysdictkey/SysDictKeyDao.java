package org.tyrande.system.dao.sysdictkey;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tyrande.system.model.sysdictkey.SysDictKeyModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统字典项表 dao 层
 *
 * @author Tyrande
 * @date 2020-08-04
 */
@Mapper
public interface SysDictKeyDao extends BaseMapper<SysDictKeyModel> {

    /**
     * 根据字典项编号删除字典值
     */
    void deleteDictValue(@Param("id") String id);

    /**
     * 根据字典项查询是否重复
     */
    SysDictKeyModel checkDictKey(@Param("dictKey") String dictKey);
}