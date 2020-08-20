package org.tyrande.generator.service;

import org.springframework.stereotype.Service;
import org.tyrande.generator.common.utils.GeneratorUtil;
import org.tyrande.generator.dao.GeneratorDao;
import org.tyrande.generator.model.queryinfo.ColumnInfo;
import org.tyrande.generator.model.queryinfo.TableInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * 代码生成 service 实现类
 *
 * @author Tyrande
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Resource
    private GeneratorDao generatorDao;

    /**
     * 代码生成
     *
     * @param tableName  表名
     * @param moduleName 模块名
     */
    @Override
    public void generatorCode(String tableName, String moduleName) {
        TableInfo tableInfo = generatorDao.getTableInfo(tableName);
        List<ColumnInfo> columns = generatorDao.getColumnInfo(tableName);
        GeneratorUtil.processCreateFile(tableInfo, columns, moduleName);
    }

}