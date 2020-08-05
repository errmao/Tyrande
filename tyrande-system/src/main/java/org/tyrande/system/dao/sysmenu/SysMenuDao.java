package org.tyrande.system.dao.sysmenu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tyrande.system.model.sysmenu.SysMenuModel;
import org.tyrande.system.model.sysmenu.SysMenuTreeModel;

import java.util.List;

/**
 * 系统菜单表 dao 层
 *
 * @author Tyrande
 * @date 2020-07-29
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuModel> {

    List<SysMenuTreeModel> getPageList();

    List<SysMenuTreeModel> getMenuByLevel(@Param("level") int level);

    SysMenuTreeModel getModelById(@Param("id")String id);
}