package org.tyrande.system.service.sysuser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.tyrande.system.model.sysuser.SysUserModel;
import org.tyrande.system.vo.sysuser.SysUserVo;
import org.tyrande.system.dao.sysuser.SysUserDao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        IPage<SysUserModel> page = sysUserDao.selectMapsPage(sysUserVo, wrapper);
        return page;
    }
}