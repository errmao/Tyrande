package org.tyrande.system.service.sysparams;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.tyrande.system.model.sysparams.SysParamsModel;
import org.tyrande.system.vo.sysparams.SysParamsVo;
import org.tyrande.system.dao.sysparams.SysParamsDao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统参数表 service 实现层
 *
 * @author Tyrande
 * @date 2020-07-31
 */
@Service("sysParamsService")
public class SysParamsServiceImpl extends ServiceImpl<SysParamsDao, SysParamsModel> implements SysParamsService {

    @Resource
    private SysParamsDao sysParamsDao;

    /**
     * 分页查询
     */
    @Override
    public IPage<SysParamsModel> getPageList(SysParamsVo sysParamsVo) {
        QueryWrapper<SysParamsModel> wrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(sysParamsVo.getParamEnName())) {
            wrapper.like("param_en_name", sysParamsVo.getParamEnName());
        }
        if (!StringUtils.isEmpty(sysParamsVo.getParamName())) {
            wrapper.like("param_name", sysParamsVo.getParamName());
        }
        IPage<SysParamsModel> page = sysParamsDao.selectMapsPage(sysParamsVo, wrapper);
        return page;
    }
}