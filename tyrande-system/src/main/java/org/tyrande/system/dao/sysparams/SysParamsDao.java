package org.tyrande.system.dao.sysparams;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.tyrande.system.model.sysparams.SysParamsModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统参数表 dao 层
 *
 * @author Tyrande
 * @date 2020-07-31
 */
@Mapper
public interface SysParamsDao extends BaseMapper<SysParamsModel> {

}