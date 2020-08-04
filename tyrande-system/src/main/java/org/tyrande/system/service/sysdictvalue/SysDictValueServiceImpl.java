package org.tyrande.system.service.sysdictvalue;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.tyrande.system.model.sysdictvalue.SysDictValueModel;
import org.tyrande.system.vo.sysdictvalue.SysDictValueVo;
import org.tyrande.system.dao.sysdictvalue.SysDictValueDao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统字典值表 service 实现层
 *
 * @author Tyrande
 * @date 2020-08-04
 */
@Service("sysDictValueService")
public class SysDictValueServiceImpl extends ServiceImpl<SysDictValueDao, SysDictValueModel> implements SysDictValueService {

    @Resource
    private SysDictValueDao sysDictValueDao;

    /**
     * 分页查询
     */
    @Override
    public IPage<SysDictValueModel> getPageList(SysDictValueVo sysDictValueVo) {
        QueryWrapper<SysDictValueModel> wrapper = new QueryWrapper();
        IPage<SysDictValueModel> page = sysDictValueDao.selectMapsPage(sysDictValueVo, wrapper);
        return page;
    }
}