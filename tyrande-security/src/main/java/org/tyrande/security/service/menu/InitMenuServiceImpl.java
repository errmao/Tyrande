package org.tyrande.security.service.menu;

import org.springframework.stereotype.Service;
import org.tyrande.common.utils.SecurityUserInfoUtil;
import org.tyrande.common.utils.TreeUtil;
import org.tyrande.security.dao.menu.InitMenuDao;
import org.tyrande.security.model.SysMenuTreeNode;

import javax.annotation.Resource;
import java.util.List;

/**
 * 初始化菜单
 *
 * @author Tyrande
 */
@Service
public class InitMenuServiceImpl implements InitMenuService{

    @Resource
    private InitMenuDao initMenuDao;

    /**
     * 根据用户权限获取菜单
     */
    @Override
    public List<SysMenuTreeNode> getMenuTreeByUser() {
        Long userId = SecurityUserInfoUtil.getCurrentUser().getId();
        List<SysMenuTreeNode> list = initMenuDao.getMenuTreeByUser(userId);
        List<SysMenuTreeNode> tree = TreeUtil.buildTree(list);
        return tree;
    }
}
