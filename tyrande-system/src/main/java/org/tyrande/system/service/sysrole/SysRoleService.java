package org.tyrande.system.service.sysrole;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.tyrande.system.model.sysrole.SysRoleModel;
import org.tyrande.system.vo.sysrole.SysRoleVo;
import java.util.List;

/**
 * 系统角色表 service 层
 *
 * @author Tyrande
 * @date 2020-08-05
 */
public interface SysRoleService extends IService<SysRoleModel> {

    /**
     * 分页查询
     */
    IPage<SysRoleModel> getPageList(SysRoleVo sysRoleVo);
}