package org.tyrande.system.dao.sysuser;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.tyrande.system.model.sysuser.SysUserModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户表 dao 层
 *
 * @author Tyrande
 * @date 2020-08-03
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserModel> {

}