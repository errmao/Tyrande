package org.tyrande.system.model.sysmenu;

import lombok.Data;
import org.tyrande.common.model.TreeNode;

import java.util.Date;

/**
 * 树节点
 *
 * @author Tyrande
 */
@Data
public class SysMenuTreeModel extends TreeNode {

    private Long id;
    private String menuName;
    private Integer menuLevel;
    private Long pid;
    private String menuUrl;
    private Date createTime;
    private Date updateTime;

}
