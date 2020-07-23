package org.tyrande.generator.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置文件信息
 *
 * @author Tyrande
 */
@Component
@ConfigurationProperties(prefix = "tyrande.generator")
public class GeneratorConfigProperty {
}
