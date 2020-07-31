package org.tyrande.system.vo.sysparams;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 系统参数表 Vo
 *
 * @author Tyrande
 * @date 2020-07-31
 */
@Data
public class SysParamsVo extends Page {

    private String paramName;
    private String paramEnName;

}