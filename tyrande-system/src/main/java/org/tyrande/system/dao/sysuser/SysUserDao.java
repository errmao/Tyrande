package org.tyrande.system.dao.sysuser;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tyrande.system.model.sysrole.SysRoleModel;
import org.tyrande.system.model.sysuser.SysUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.tyrande.system.model.sysuser.SysUserRole;

import java.util.List;

/**
 * 系统用户表 dao 层
 *
 * @author Tyrande
 * @date 2020-08-03
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserModel> {

    List<Long> selectUserRole(@Param("id") String id);

    List<SysRoleModel> selectRoleList();

    void deleteByUserId(@Param("id") String id);

    void saveUserRole(SysUserRole role);
}