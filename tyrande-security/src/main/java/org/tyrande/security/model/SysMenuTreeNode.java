package org.tyrande.security.model;

import lombok.Data;
import org.tyrande.common.model.TreeNode;

import java.util.Date;

/**
 * 菜单树节点
 *
 * @author Tyrande
 */
@Data
public class SysMenuTreeNode extends TreeNode {

    private Integer id;

    private String menuName;

    private Integer menuLevel;

    private Integer pid;

    private String menuLogo;

    private String menuUrl;

    private Date createTime;

    private Date updateTime;
}