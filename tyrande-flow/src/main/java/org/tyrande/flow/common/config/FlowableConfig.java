package org.tyrande.flow.common.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 * 工作流配置类
 *
 * @author Tyrande
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.flow")
@Data
public class FlowableConfig {

    private String username;
    private String password;
    private String url;
    private String driverClassName;

    /**
     * 初始化流程引擎
     */
    @Primary
    @Bean("processEngine")
    public ProcessEngine processEngine() {
        log.info("[流程引擎初始化开始...........]");
        // 流程引擎配置
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcDriver(driverClassName)
                .setJdbcUrl(url)
                .setJdbcUsername(username)
                .setJdbcPassword(password)
                .setDatabaseSchemaUpdate("create")
                .setDatabaseSchema("tyrande_flow")
                .setTablePrefixIsSchema(true)
                .setDatabaseTablePrefix("tyrande_flow.")
                .setAsyncExecutorActivate(false)
                .setAsyncHistoryExecutorActivate(false)
                // 解决流程图乱码
                .setActivityFontName("微软雅黑")
                .setLabelFontName("微软雅黑")
                .setAnnotationFontName("微软雅黑");
        // 初始化流程引擎对象
        ProcessEngine processEngine = cfg.buildProcessEngine();
        log.info("[流程引擎初始化结束...........]");
        return processEngine;
    }
}
