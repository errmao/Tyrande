package org.tyrande.generator.common.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.tyrande.common.exception.customize.CustomException;

import java.io.IOException;

/**
 * 模板工具类
 *
 * @author Tyrande
 */
@Slf4j
public class TemplateUtil {

    /**
     * 私有构造方法
     */
    private TemplateUtil() {
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
     */
    public static Template getTemplate(String templateName) {
        try {
            return CONFIGURATION.getTemplate(templateName);
        } catch (IOException e) {
            throw new CustomException("获取模板对象失败", e);
        }
    }

    /**
     * 清空模板对象的缓存
     */
    public static void clearTemplateCache() {
        CONFIGURATION.clearTemplateCache();
    }
}
