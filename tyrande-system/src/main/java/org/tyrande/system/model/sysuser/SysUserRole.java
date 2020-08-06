package org.tyrande.system.model.sysuser;

import lombok.Data;

/**
 * @author Tyrande
 */
@Data
public class SysUserRole {

    private String id;
    private String userName;
    private Long[] roles;
}
