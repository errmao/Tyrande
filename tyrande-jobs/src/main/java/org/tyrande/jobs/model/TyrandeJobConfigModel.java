package org.tyrande.jobs.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 执行器配置类信息
 *
 * @author Tyrande
 */
@Data
@Component
@ConfigurationProperties(prefix = "xxl.job.executor")
public class TyrandeJobConfigModel {

    /**
     * 执行器名称
     */
    private String appname;
    /**
     * 执行器地址
     */
    private String address;
    /**
     * 执行器IP
     */
    private String ip;
    /**
     * 执行器端口号
     */
    private int port;
    /**
     * 日志路径
     */
    private String logpath;
    /**
     * 日志保存天数
     */
    private int logretentiondays;
}
