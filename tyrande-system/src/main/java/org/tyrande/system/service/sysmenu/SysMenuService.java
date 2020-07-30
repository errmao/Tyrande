package org.tyrande.system.service.sysmenu;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.tyrande.system.model.sysmenu.SysMenuModel;
import org.tyrande.system.vo.sysmenu.SysMenuVo;

/**
 * 系统菜单表 service 层
 *
 * @author Tyrande
 * @date 2020-07-29
 */
public interface SysMenuService extends IService<SysMenuModel> {

    /**
     * 分页查询
     */
    IPage<SysMenuModel> getPageList(SysMenuVo sysMenuVo);

}