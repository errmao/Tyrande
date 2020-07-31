package org.tyrande.system.service.sysparams;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.tyrande.system.model.sysparams.SysParamsModel;
import org.tyrande.system.vo.sysparams.SysParamsVo;
import java.util.List;

/**
 * 系统参数表 service 层
 *
 * @author Tyrande
 * @date 2020-07-31
 */
public interface SysParamsService extends IService<SysParamsModel> {

    /**
     * 分页查询
     */
    IPage<SysParamsModel> getPageList(SysParamsVo sysParamsVo);
}