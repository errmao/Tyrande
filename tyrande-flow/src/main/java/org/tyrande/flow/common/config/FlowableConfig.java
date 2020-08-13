package org.tyrande.flow.common.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 配置类
 *
 * @author Tyrande
 */
@Slf4j
@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Resource
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    @Value("${flowable.data-source}")
    private String dataSource;

    /**
     * 引擎高级配置
     */
    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        // 设置指定的数据源
        if (!StringUtils.isEmpty(dataSource)) {
            springProcessEngineConfiguration.setDataSource(dynamicRoutingDataSource.getDataSource("flow"));
        }
    }

    /**
     * 启动任务
     */
    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final TaskService taskService) {
        return strings -> {
            log.info("[流程定义数量]：{} 个", repositoryService.createProcessDefinitionQuery().count());
            log.info("[流程任务数量]：{} 个", taskService.createTaskQuery().count());
        };
    }
}
