package org.tyrande.system.service.sysmenu;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tyrande.common.utils.TreeUtil;
import org.tyrande.system.dao.sysmenu.SysMenuDao;
import org.tyrande.system.model.sysmenu.SysMenuModel;
import org.tyrande.system.model.sysmenu.SysMenuTreeModel;

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
    public List<SysMenuTreeModel> getPageList() {
        List<SysMenuTreeModel> list = sysMenuDao.getPageList();
        List<SysMenuTreeModel> tree = TreeUtil.buildTree(list);
        return tree;
    }
}