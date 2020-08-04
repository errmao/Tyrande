package org.tyrande.system.service.sysdictkey;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.tyrande.system.model.sysdictkey.SysDictKeyModel;
import org.tyrande.system.vo.sysdictkey.SysDictKeyVo;
import org.tyrande.system.dao.sysdictkey.SysDictKeyDao;

import org.springframework.stereotype.Service;

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
        IPage<SysDictKeyModel> page = sysDictKeyDao.selectMapsPage(sysDictKeyVo, wrapper);
        return page;
    }
}