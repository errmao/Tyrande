package org.tyrande.system.vo.sysrole;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 系统角色表 Vo
 *
 * @author Tyrande
 * @date 2020-08-05
 */
@Data
public class SysRoleVo extends Page {

    private String roleName;

}