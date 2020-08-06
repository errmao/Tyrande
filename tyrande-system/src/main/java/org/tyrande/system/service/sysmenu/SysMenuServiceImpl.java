package org.tyrande.system.service.sysmenu;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tyrande.common.utils.TreeUtil;
import org.tyrande.system.dao.sysmenu.SysMenuDao;
import org.tyrande.system.model.sysmenu.SysMenuModel;
import org.tyrande.system.model.sysmenu.SysMenuTreeModel;

import javax.annotation.Resource;
import java.util.ArrayList;
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
     * 菜单树列表
     */
    @Override
    public List<SysMenuTreeModel> getPageList() {
        List<SysMenuTreeModel> list = sysMenuDao.getPageList();
        List<SysMenuTreeModel> tree = TreeUtil.buildTree(list);
        return tree;
    }

    /**
     * 根据菜单层级查询菜单
     */
    @Override
    public List<SysMenuTreeModel> getMenuByLevel(String level) {
        List<SysMenuTreeModel> list = new ArrayList<>();
        SysMenuTreeModel model = new SysMenuTreeModel();
        model.setId(-1L);
        model.setPid(-2L);
        model.setMenuLevel(0);
        model.setMenuName("根菜单");
        if (!level.equals(1)) {
            list.addAll(sysMenuDao.getMenuByLevel(Integer.parseInt(level) - 1));
            list.add(model);
            List<SysMenuTreeModel> tree = TreeUtil.buildTreeRoot(list);
            return tree;
        }
        return list;
    }

}