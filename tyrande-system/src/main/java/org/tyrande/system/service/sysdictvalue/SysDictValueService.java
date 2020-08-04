package org.tyrande.system.service.sysdictvalue;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.tyrande.system.model.sysdictvalue.SysDictValueModel;
import org.tyrande.system.vo.sysdictvalue.SysDictValueVo;
import java.util.List;

/**
 * 系统字典值表 service 层
 *
 * @author Tyrande
 * @date 2020-08-04
 */
public interface SysDictValueService extends IService<SysDictValueModel> {

    /**
     * 分页查询
     */
    IPage<SysDictValueModel> getPageList(SysDictValueVo sysDictValueVo);
}