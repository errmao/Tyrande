package org.tyrande.system.service.sysuser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.tyrande.system.dao.sysuser.SysUserDao;
import org.tyrande.system.model.sysrole.SysRoleModel;
import org.tyrande.system.model.sysuser.SysUserModel;
import org.tyrande.system.model.sysuser.SysUserRole;
import org.tyrande.system.vo.sysuser.SysUserVo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户表 service 实现层
 *
 * @author Tyrande
 * @date 2020-08-03
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserModel> implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    /**
     * 分页查询
     */
    @Override
    public IPage<SysUserModel> getPageList(SysUserVo sysUserVo) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(sysUserVo.getLoginCode())) {
            wrapper.like("login_code", sysUserVo.getLoginCode());
        }
        if (!StringUtils.isEmpty(sysUserVo.getUserName())) {
            wrapper.like("user_name", sysUserVo.getUserName());
        }
        // 查询字段排除密码
        wrapper.select(SysUserModel.class, info -> !info.getColumn().equals("password"));
        IPage<SysUserModel> page = sysUserDao.selectMapsPage(sysUserVo, wrapper);
        return page;
    }

    /**
     * 查询用户角色信息
     *
     * @param id
     */
    @Override
    public Map<String, Object> getUserRoleInfo(String id) {
        Map<String, Object> map = new HashMap<>(12);
        List<Long> list = sysUserDao.selectUserRole(id);
        List<SysRoleModel> roleList = sysUserDao.selectRoleList();
        map.put("userRole", list);
        map.put("roleList", roleList);
        return map;
    }

    /**
     * 保存用户角色
     *
     * @param role
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doConfigSave(SysUserRole role) {
        // 删除
        sysUserDao.deleteByUserId(role.getId());
        // 插入
        sysUserDao.saveUserRole(role);
    }
}