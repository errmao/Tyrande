package org.tyrande.security.service.menu;

import org.springframework.stereotype.Service;
import org.tyrande.common.utils.SecurityUserInfoUtil;
import org.tyrande.common.utils.TreeUtil;
import org.tyrande.security.dao.menu.InitMenuDao;
import org.tyrande.security.model.menu.InitMenu;

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
    public List<InitMenu> getMenuByUser() {
        Long userId = SecurityUserInfoUtil.getCurrentUser().getId();
        List<InitMenu> list = initMenuDao.getMenuByUser(userId);
        return list;
    }
}
