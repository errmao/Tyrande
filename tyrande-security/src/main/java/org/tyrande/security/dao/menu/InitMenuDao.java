package org.tyrande.security.dao.menu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tyrande.security.model.menu.InitMenu;

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
    List<InitMenu> getMenuByUser(@Param("userId") Long userId);
}
