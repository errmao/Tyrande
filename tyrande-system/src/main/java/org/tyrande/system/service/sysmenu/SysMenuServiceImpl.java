package org.tyrande.system.service.sysmenu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tyrande.system.dao.sysmenu.SysMenuDao;
import org.tyrande.system.model.sysmenu.SysMenuModel;
import org.tyrande.system.vo.sysmenu.SysMenuVo;

import javax.annotation.Resource;

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
}