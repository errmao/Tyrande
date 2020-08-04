package org.tyrande.system.dao.sysdictkey;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

}