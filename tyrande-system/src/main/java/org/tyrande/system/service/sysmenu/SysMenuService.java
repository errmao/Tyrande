package org.tyrande.system.service.sysmenu;

import com.baomidou.mybatisplus.extension.service.IService;
import org.tyrande.system.model.sysmenu.SysMenuModel;
import org.tyrande.system.model.sysmenu.SysMenuTreeModel;

import java.util.List;

/**
 * 系统菜单表 service 层
 *
 * @author Tyrande
 * @date 2020-07-29
 */
public interface SysMenuService extends IService<SysMenuModel> {

    /**
     * 查询菜单列表
     */
    List<SysMenuTreeModel> getPageList();

}