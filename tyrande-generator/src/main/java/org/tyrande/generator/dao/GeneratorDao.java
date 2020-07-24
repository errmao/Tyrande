package org.tyrande.generator.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tyrande.generator.model.queryinfo.ColumnInfo;
import org.tyrande.generator.model.queryinfo.TableInfo;

import java.util.List;

/**
 * 代码生成dao
 *
 * @author Tyrande
 */
@Mapper
public interface GeneratorDao {

    /**
     * 查询表信息
     *
     * @param tableName 表名
     * @return
     */
    TableInfo getTableInfo(@Param("tableName") String tableName);

    /**
     * 查询字段信息
     *
     * @param tableName 表名
     * @return
     */
    List<ColumnInfo> getColumnInfo(@Param("tableName") String tableName);
}
