package org.tyrande.security.dao.menu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tyrande.security.model.SysMenuTreeNode;

import java.util.List;

/**
 * 初始化菜单
 *
 * @author Tyrande
 */
@Mapper
public interface InitMenuDao {

    /**
     * 初始化菜单
     */
    List<SysMenuTreeNode> getMenuTreeByUser(@Param("userId") Long userId);
}
