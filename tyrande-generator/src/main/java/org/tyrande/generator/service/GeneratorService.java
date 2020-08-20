package org.tyrande.generator.service;

/**
 * 代码生成 service
 *
 * @author Tyrande
 */
public interface GeneratorService {

    /**
     * 代码生成
     *
     * @param tableName  表名
     * @param moduleName 模块名
     */
    void generatorCode(String tableName, String moduleName);
}
