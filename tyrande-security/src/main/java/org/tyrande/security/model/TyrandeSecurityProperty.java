package org.tyrande.security.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类
 *
 * @author Tyrande
 */
@Component
@ConfigurationProperties(prefix = "tyrande.security")
@Data
public class TyrandeSecurityProperty {

    /**
     * 设置登录地址
     */
    private String loginUrl;

    /**
     * 设置登出地址
     */
    private String logoutUrl;
}
