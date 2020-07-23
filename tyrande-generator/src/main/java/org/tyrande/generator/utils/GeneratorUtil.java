package org.tyrande.generator.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

/**
 * 模板引擎工具类
 *
 * @author Tyrande
 */
@Slf4j
public class GeneratorUtil {

    /**
     * 私有构造方法
     */
    private GeneratorUtil() {
    }

    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_30);

    static {
        //这里比较重要，用来指定加载模板所在的路径
        CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(FreeMarkerTemplateUtils.class, "/templates"));
        CONFIGURATION.setDefaultEncoding("UTF-8");
        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
    }

    /**
     * 获取模板对象
     *
     * @param templateName 模板名称
     * @return
     * @throws IOException
     */
    public static Template getTemplate(String templateName) throws IOException {
        try {
            return CONFIGURATION.getTemplate(templateName);
        } catch (IOException e) {
            log.error("获取模板对象失败");
            throw new RuntimeException("获取模板对象失败");
        }
    }

    /**
     * 清空模板对象的缓存
     */
    public static void clearCache() {
        CONFIGURATION.clearTemplateCache();
    }
}
