package org.tyrande.system.dao.sysrole;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.tyrande.system.model.sysrole.SysRoleModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统角色表 dao 层
 *
 * @author Tyrande
 * @date 2020-08-05
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleModel> {

}