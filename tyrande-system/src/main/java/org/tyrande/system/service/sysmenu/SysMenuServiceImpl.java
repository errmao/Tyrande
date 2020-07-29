package org.tyrande.system.service.sysmenu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.tyrande.common.utils.SecurityUserInfoUtil;
import org.tyrande.common.utils.TreeUtil;
import org.tyrande.system.model.sysmenu.SysMenuModel;
import org.tyrande.system.model.sysmenu.SysMenuTreeNode;
import org.tyrande.system.vo.sysmenu.SysMenuVo;
import org.tyrande.system.dao.sysmenu.SysMenuDao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单表 service 实现层
 *
 * @author Tyrande
 * @date 2020-07-29
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuModel> implements org.tyrande.system.service.sysmenu.SysMenuService {

    @Resource
    private SysMenuDao sysMenuDao;

    /**
     * 分页查询
     */
    @Override
    public IPage<SysMenuModel> getPageList(SysMenuVo sysMenuVo) {
        QueryWrapper<SysMenuModel> wrapper = new QueryWrapper();
        IPage<SysMenuModel> page = sysMenuDao.selectMapsPage(sysMenuVo, wrapper);
        return page;
    }

    /**
     * 根据用户权限获取菜单
     */
    @Override
    public List<SysMenuTreeNode> getMenuTreeByUser() {
        Long userId = SecurityUserInfoUtil.getCurrentUser().getId();
        List<SysMenuTreeNode> list = sysMenuDao.getMenuTreeByUser(userId);
        List<SysMenuTreeNode> tree = TreeUtil.buildTree(list);
        return tree;
    }
}