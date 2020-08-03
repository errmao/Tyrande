package org.tyrande.generator.common.enums;

/**
 * 模板类型枚举
 *
 * @author Tyrande
 */
public enum TemplateTypeEnum {
    /**
     * js
     */
    JS("js", ".js.ftl"),
    /**
     * vue
     */
    VUE("vue", ".vue.ftl"),
    /**
     * Controller
     */
    CONTROLLER("controller", "Controller.java.ftl"),
    /**
     * 接口实现
     */
    SERVICE_IMPL("service", "ServiceImpl.java.ftl"),
    /**
     * 接口
     */
    SERVICE("service", "Service.java.ftl"),
    /**
     * Mapper
     */
    MAPPER("mappers", "Mapping.xml.ftl"),
    /**
     * DAO
     */
    DAO("dao", "Dao.java.ftl"),
    /**
     * VO实体
     */
    VO("vo", "Vo.java.ftl"),
    /**
     * 实体
     */
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
