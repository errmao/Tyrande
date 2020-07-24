package org.tyrande.generator.common.enums;

/**
 * 模板类型枚举
 *
 * @author Tyrande
 */
public enum TemplateTypeEnum {

    MODLE("model", "Model.java.ftl");

    private final String type;
    private final String fileName;

    TemplateTypeEnum(String type, String fileName) {
        this.fileName = fileName;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getFileName() {
        return fileName;
    }
}
