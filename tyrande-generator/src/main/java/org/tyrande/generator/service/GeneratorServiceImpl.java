package org.tyrande.generator.service;

import org.springframework.stereotype.Service;
import org.tyrande.generator.dao.GeneratorDao;

import javax.annotation.Resource;

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
     * @param tableName 表名
     */
    @Override
    public void generatorCode(String tableName) {
        System.out.println(generatorDao.getTableInfo("sys_user").toString());
        generatorDao.getColumnInfo("sys_user").forEach(e -> System.err.println(e.toString()));
    }

    /*public static void main(String[] args) throws IOException, TemplateException {
        Template template = GeneratorUtil.getTemplate("Model.ftl");

        File file = FileUtil.touch ("D://data/TestModel.java");
        FileOutputStream fos = new FileOutputStream(file);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packageName","org.dddd");
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }*/
}
