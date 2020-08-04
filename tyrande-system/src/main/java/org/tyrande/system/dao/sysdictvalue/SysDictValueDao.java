package org.tyrande.system.dao.sysdictvalue;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.tyrande.system.model.sysdictvalue.SysDictValueModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统字典值表 dao 层
 *
 * @author Tyrande
 * @date 2020-08-04
 */
@Mapper
public interface SysDictValueDao extends BaseMapper<SysDictValueModel> {

}