package org.tyrande.generator.common.enums;

/**
 * 字段类型映射枚举
 *
 * @author Tyrande
 */
public enum FieldTypeEnum {

    /**
     * 日期
     */
    DATETIME("datetime", "Date"),
    /**
     * 字符类型
     */
    LONGTEXT("longtext", "String"),
    /**
     * 字符类型
     */
    VARCHAR("varchar", "String"),
    /**
     * tinyint类型
     */
    TINYINT("tinyint", "Integer"),
    /**
     * int类型
     */
    INT("int", "Integer");


    /**
     * 数据库类型
     */
    private final String jdbcType;
    /**
     * Java类型
     */
    private final String javaType;

    FieldTypeEnum(String jdbcType, String javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public static String getJavaType(String jdbcType) {
        for (FieldTypeEnum enums : FieldTypeEnum.values()) {
            if (enums.getJdbcType().equals(jdbcType)) {
                return enums.getJavaType();
            }
        }
        return "";
    }

}
