package org.tyrande.generator.common.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.tyrande.common.constant.NormalConstants;
import org.tyrande.common.exception.customize.CustomException;
import org.tyrande.common.utils.SpringContextUtil;
import org.tyrande.common.utils.StringChangeUtil;
import org.tyrande.generator.common.enums.FieldTypeEnum;
import org.tyrande.generator.common.enums.TemplateTypeEnum;
import org.tyrande.generator.model.GeneratorConfigProperty;
import org.tyrande.generator.model.geninfo.GeneratorDataModel;
import org.tyrande.generator.model.geninfo.GeneratorFieldModel;
import org.tyrande.generator.model.queryinfo.ColumnInfo;
import org.tyrande.generator.model.queryinfo.TableInfo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板创建生成工具类
 *
 * @author Tyrande
 */
@Slf4j
public class GeneratorUtil {

    /**
     * 生成对应代码文件
     *
     * @param tableInfo  表信息
     * @param columns    列信息
     * @param moduleName 模块名
     */
    public static void processCreateFile(TableInfo tableInfo, List<ColumnInfo> columns, String moduleName) {
        GeneratorDataModel generatorDataModel = processDataModel(tableInfo, columns, moduleName);
        // 生成 model
        processCreateFile(generatorDataModel, TemplateTypeEnum.MODLE);
        // 生成 vo
        processCreateFile(generatorDataModel, TemplateTypeEnum.VO);
        // 生成 dao
        processCreateFile(generatorDataModel, TemplateTypeEnum.DAO);
        // 生成 mapper
        processCreateFile(generatorDataModel, TemplateTypeEnum.MAPPER);
        // 生成 service
        processCreateFile(generatorDataModel, TemplateTypeEnum.SERVICE);
        // 生成 service 实现类
        processCreateFile(generatorDataModel, TemplateTypeEnum.SERVICE_IMPL);
    }

    /**
     * 创建对应文件
     *
     * @param generatorDataModel 数据
     * @param typeEnum           类型
     */
    private static void processCreateFile(GeneratorDataModel generatorDataModel,
                                          TemplateTypeEnum typeEnum) {

        GeneratorConfigProperty generatorConfigProperty
                = (GeneratorConfigProperty) SpringContextUtil.getBean("generatorConfigProperty");

        Template template = TemplateUtil.getTemplate(typeEnum.getFileName());
        String fileName = typeEnum.getFileName();
        int index = fileName.indexOf(NormalConstants.FTL);
        String path = new StringBuffer(generatorConfigProperty.getFilePath())
                .append(generatorDataModel.getModuleName())
                .append(NormalConstants.SLASH).append(typeEnum.getType())
                .append(NormalConstants.SLASH)
                .append(generatorDataModel.getClassNameLower())
                .append(NormalConstants.SLASH)
                .append(generatorDataModel.getClassName())
                .append(typeEnum.getFileName(), 0, index).toString();
        File file = FileUtil.touch(path);
        // try with 关闭资源
        try (FileOutputStream fos = new FileOutputStream(file);
             Writer out = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8), 10240)) {
            // 生成文件
            template.process(generatorDataModel, out);
        } catch (Exception e) {
            throw new CustomException("通过模板生成代码文件异常：" + typeEnum.getType(), e);
        }

    }

    /**
     * 根据表和字段信息生成模板需要的数据实体
     *
     * @param tableInfo  表信息
     * @param columns    表字段信息
     * @param moduleName 模块名
     * @return
     */
    private static GeneratorDataModel processDataModel(TableInfo tableInfo,
                                                       List<ColumnInfo> columns,
                                                       String moduleName) {
        GeneratorConfigProperty generatorConfigProperty =
                (GeneratorConfigProperty) SpringContextUtil.getBean("generatorConfigProperty");

        GeneratorDataModel generatorDataModel = new GeneratorDataModel();
        // 注释作者
        generatorDataModel.setAuthor(generatorConfigProperty.getAuthor());
        // 注释时间
        generatorDataModel.setDatetime(DateTime.now().toDateStr());
        // 类名
        if (generatorConfigProperty.getExcludePrefix()) {
            String className = tableInfo.getTableName();
            // 去除第一个下划线之前的内容
            int index = className.indexOf(NormalConstants.UNDER_LINE);
            className = className.substring(index + 1);
            generatorDataModel.setClassName(StringChangeUtil.upperTable(className));
            generatorDataModel.setClassNameLower(className.toLowerCase());
        } else {
            generatorDataModel.setClassName(
                    StringChangeUtil.upperTable(tableInfo.getTableName()));
            generatorDataModel.setClassNameLower(
                    StringChangeUtil.upperTable(tableInfo.getTableName()).toLowerCase());
        }
        generatorDataModel.setClassFirstNameLower(StringChangeUtil.toLowerCaseFirstOne(generatorDataModel.getClassName()));
        // 模块名
        generatorDataModel.setModuleName(moduleName);
        // 包名
        StringBuffer sb = new StringBuffer();
        sb.append(NormalConstants.PROJECT_PRE).append(NormalConstants.POINT).append(moduleName);
        generatorDataModel.setPackageName(sb.toString());
        // 表名
        generatorDataModel.setTableName(tableInfo.getTableName());
        // 表注释
        generatorDataModel.setTableNameRemark(tableInfo.getTableComment());

        // 列
        List<GeneratorFieldModel> fields = processFileds(columns);
        generatorDataModel.setColumns(fields);

        return generatorDataModel;
    }

    /**
     * 字段处理
     *
     * @param columns 所有字段
     * @return
     */
    private static List<GeneratorFieldModel> processFileds(List<ColumnInfo> columns) {
        List<GeneratorFieldModel> list = new ArrayList<>();
        for (ColumnInfo columnInfo : columns) {

            if (columnInfo.getIsPk()
                || NormalConstants.CREATE_TIME.equals(columnInfo.getColumnName())
                || NormalConstants.UPDATE_TIME.equals(columnInfo.getColumnName())) {
                continue;
            }
            GeneratorFieldModel generatorFieldModel = new GeneratorFieldModel();

            String columnType = columnInfo.getColumnType();
            int index = columnType.indexOf(NormalConstants.LEFT_BRACKETS);
            if (index != -1) {
                String jdbcType = columnType.substring(0, index);
                generatorFieldModel.setJavaType(FieldTypeEnum.getJavaType(jdbcType));
            }
            generatorFieldModel.setColumnField(columnInfo.getColumnName());
            generatorFieldModel.setColumnComment(columnInfo.getColumnComment());
            generatorFieldModel.setJavaField(StringChangeUtil.underlineToHump(columnInfo.getColumnName()));
            list.add(generatorFieldModel);
        }
        return list;
    }
}
