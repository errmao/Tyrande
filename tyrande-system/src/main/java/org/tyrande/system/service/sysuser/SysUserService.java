package org.tyrande.system.service.sysuser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.tyrande.system.model.sysuser.SysUserModel;
import org.tyrande.system.model.sysuser.SysUserRole;
import org.tyrande.system.vo.sysuser.SysUserVo;
import java.util.List;
import java.util.Map;

/**
 * 系统用户表 service 层
 *
 * @author Tyrande
 * @date 2020-08-03
 */
public interface SysUserService extends IService<SysUserModel> {

    /**
     * 分页查询
     */
    IPage<SysUserModel> getPageList(SysUserVo sysUserVo);

    /**
     * 查询用户角色信息
     */
    Map<String, Object> getUserRoleInfo(String id);

    /**
     * 保存用户角色
     */
    void doConfigSave(SysUserRole role);
}