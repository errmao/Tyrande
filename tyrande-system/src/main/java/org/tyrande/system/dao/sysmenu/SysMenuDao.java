package org.tyrande.system.dao.sysmenu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.tyrande.system.model.sysmenu.SysMenuModel;

/**
 * 系统菜单表 dao 层
 *
 * @author Tyrande
 * @date 2020-07-29
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuModel> {

}