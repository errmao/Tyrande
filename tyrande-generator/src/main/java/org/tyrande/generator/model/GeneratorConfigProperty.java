package org.tyrande.generator.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置文件信息
 *
 * @author Tyrande
 */
@Component
@ConfigurationProperties(prefix = "tyrande.generator")
@Data
public class GeneratorConfigProperty {

    /**
     * 作者
     */
    private String author;

    /**
     * 是否去除前缀
     */
    private Boolean excludePrefix;

    /**
     * 生成文件路径前缀
     */
    private String filePath;
}
