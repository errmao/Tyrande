package org.tyrande.generator.model;

import lombok.Data;

/**
 * 字段信息
 *
 * @author Tyrande
 */
@Data
public class ColumnInfo {

    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 是否主键
     */
    private Integer isPk;
    /**
     * 顺序
     */
    private Integer sort;
    /**
     * 字段注释
     */
    private String columnComment;
    /**
     * 字段类型
     */
    private String columnType;
}
