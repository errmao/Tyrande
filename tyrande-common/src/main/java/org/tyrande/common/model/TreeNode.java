package org.tyrande.common.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 树型模型
 *
 * @author Tyrande
 */
@Data
public class TreeNode {

    private Integer id;
    private Integer pid;
    private String name;
    private List<TreeNode> sub = new ArrayList<>();

    public TreeNode() {
    }

    public TreeNode(int id, int pid) {
        this.id = id;
        this.pid = pid;
    }

    public TreeNode(int id, int pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }
}
