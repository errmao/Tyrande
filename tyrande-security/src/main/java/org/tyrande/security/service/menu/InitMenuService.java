package org.tyrande.security.service.menu;

import org.tyrande.security.model.SysMenuTreeNode;

import java.util.List;

/**
 * 初始化菜单接口
 *
 * @author Tyrande
 */
public interface InitMenuService {

    /**
     * 根据用户权限获取菜单
     */
    List<SysMenuTreeNode> getMenuTreeByUser();
}
