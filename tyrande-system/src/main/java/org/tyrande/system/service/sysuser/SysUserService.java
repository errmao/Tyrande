package org.tyrande.system.service.sysuser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.tyrande.system.model.sysuser.SysUserModel;
import org.tyrande.system.vo.sysuser.SysUserVo;
import java.util.List;

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
}