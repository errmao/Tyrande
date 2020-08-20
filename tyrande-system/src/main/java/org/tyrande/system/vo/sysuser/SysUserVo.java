package org.tyrande.system.vo.sysuser;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 系统用户表 Vo
 *
 * @author Tyrande
 * @date 2020-08-03
 */
@Data
public class SysUserVo extends Page {

    private String loginCode;
    private String userName;

}