package org.tyrande.generator.model;

import lombok.Data;

/**
 * 表信息
 *
 * @author Tyrande
 */
@Data
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 表注释
     */
    private String tableComment;
}
