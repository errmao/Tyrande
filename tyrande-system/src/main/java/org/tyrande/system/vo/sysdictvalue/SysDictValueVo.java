package org.tyrande.system.vo.sysdictvalue;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 系统字典值表 Vo
 *
 * @author Tyrande
 * @date 2020-08-04
 */
@Data
public class SysDictValueVo extends Page {

    @NotNull(message = "字典项编号不能为空")
    private String dictKeyId;
}