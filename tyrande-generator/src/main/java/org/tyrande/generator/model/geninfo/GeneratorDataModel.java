package org.tyrande.generator.model.geninfo;

import lombok.Data;

import java.util.List;

/**
 * 数据实体
 *
 * @author Tyrande
 */
@Data
public class GeneratorDataModel {

    /**
     * 注释作者
     */
    private String author;

    /**
     * 注释日期
     */
    private String datetime;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表名注释
     */
    private String tableNameRemark;

    /**
     * 类名前缀
     */
    private String className;

    /**
     * 小写类名前缀
     */
    private String classNameLower;

    /**
     * 实体字段
     */
    private List<GeneratorFieldModel> columns;
}
