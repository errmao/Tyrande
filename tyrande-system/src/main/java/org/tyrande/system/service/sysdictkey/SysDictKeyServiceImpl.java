package org.tyrande.system.service.sysdictkey;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tyrande.system.dao.sysdictkey.SysDictKeyDao;
import org.tyrande.system.model.sysdictkey.SysDictKeyModel;
import org.tyrande.system.vo.sysdictkey.SysDictKeyVo;

import javax.annotation.Resource;

/**
 * 系统字典项表 service 实现层
 *
 * @author Tyrande
 * @date 2020-08-04
 */
@Service("sysDictKeyService")
public class SysDictKeyServiceImpl extends ServiceImpl<SysDictKeyDao, SysDictKeyModel> implements SysDictKeyService {

    @Resource
    private SysDictKeyDao sysDictKeyDao;

    /**
     * 分页查询
     */
    @Override
    public IPage<SysDictKeyModel> getPageList(SysDictKeyVo sysDictKeyVo) {
        QueryWrapper<SysDictKeyModel> wrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(sysDictKeyVo.getDictKey())) {
            wrapper.like("dict_key", sysDictKeyVo.getDictKey());
        }
        if (!StringUtils.isEmpty(sysDictKeyVo.getDictName())) {
            wrapper.like("dict_name", sysDictKeyVo.getDictName());
        }
        IPage<SysDictKeyModel> page = sysDictKeyDao.selectMapsPage(sysDictKeyVo, wrapper);
        return page;
    }
}