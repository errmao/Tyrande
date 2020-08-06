package org.tyrande.system.dao.sysrole;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tyrande.system.model.sysrole.RoleMenuVo;
import org.tyrande.system.model.sysrole.SysRoleModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统角色表 dao 层
 *
 * @author Tyrande
 * @date 2020-08-05
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleModel> {

    /**
     * 查询角色拥有的菜单权限
     */
    List<Long> getRoleMenu(@Param("id") String id);

    /**
     * 删除角色的菜单权限
     * @param role
     */
    void deleteByRoleId(@Param("role") Long role);

    /**
     * 保存角色的菜单权限
     */
    void saveRoleMenu(RoleMenuVo roleMenuVo);
}