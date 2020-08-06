package org.tyrande.system.model.sysrole;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 角色菜单保存
 *
 * @author Tyrande
 */
@Data
public class RoleMenuVo {

    @NotNull(message = "角色编号不能为空")
    private Long role;
    private Long[] menu;
}
