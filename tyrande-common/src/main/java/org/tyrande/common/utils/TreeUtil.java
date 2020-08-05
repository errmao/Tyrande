package org.tyrande.common.utils;


import org.tyrande.common.model.TreeNode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 树型数据工具类
 *
 * @author tinyQ
 * @date 2020/5/7 15:21
 */
public class TreeUtil {

    /**
     * 构建树（上级节点是0则为根节点）
     *
     * @param nodes 数据集合
     * @return
     */
    public static <T extends TreeNode> List<T> buildTree(List<T> nodes) {
        Map<Long, List<TreeNode>> sub = nodes.stream()
                .filter(node -> node.getPid() != -1).collect(Collectors.groupingBy(node -> node.getPid()));
        nodes.forEach(node -> node.setSub(sub.get(node.getId())));
        return nodes.stream().filter(node -> node.getPid() == -1).collect(Collectors.toList());
    }

    /**
     * 构建树（根节点是root）
     */
    public static <T extends TreeNode> List<T> buildTreeRoot(List<T> nodes) {
        Map<Long, List<TreeNode>> sub = nodes.stream()
                .filter(node -> node.getPid() != -2L).collect(Collectors.groupingBy(node -> node.getPid()));
        nodes.forEach(node -> node.setSub(sub.get(node.getId())));
        return nodes.stream().filter(node -> node.getPid() == -2L).collect(Collectors.toList());
    }
}
