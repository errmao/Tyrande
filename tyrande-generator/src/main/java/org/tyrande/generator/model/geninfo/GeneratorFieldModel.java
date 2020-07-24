package org.tyrande.generator.model.geninfo;

import lombok.Data;

/**
 * 字段信息
 *
 * @author Tyrande
 */
@Data
public class GeneratorFieldModel {

    /**
     * 字段注释
     */
    private String columnComment;
    /**
     * 字段类型
     */
    private String javaType;
    /**
     * 字段名称
     */
    private String javaField;
}
