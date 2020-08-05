package org.tyrande.system.service.sysrole;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.tyrande.system.model.sysrole.SysRoleModel;
import org.tyrande.system.vo.sysrole.SysRoleVo;
import org.tyrande.system.dao.sysrole.SysRoleDao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统角色表 service 实现层
 *
 * @author Tyrande
 * @date 2020-08-05
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleModel> implements SysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    /**
     * 分页查询
     */
    @Override
    public IPage<SysRoleModel> getPageList(SysRoleVo sysRoleVo) {
        QueryWrapper<SysRoleModel> wrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(sysRoleVo.getRoleName())) {
            wrapper.like("role_name", sysRoleVo.getRoleName());
        }
        IPage<SysRoleModel> page = sysRoleDao.selectMapsPage(sysRoleVo, wrapper);
        return page;
    }
}