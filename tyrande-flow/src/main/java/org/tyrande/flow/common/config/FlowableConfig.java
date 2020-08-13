package org.tyrande.flow.common.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 配置类
 *
 * @author Tyrande
 */
@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Resource
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    @Value("${flowable.data-source}")
    private String dataSource;

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        // 设置指定的数据源
        if (!StringUtils.isEmpty(dataSource)) {
            springProcessEngineConfiguration.setDataSource(dynamicRoutingDataSource.getDataSource("flow"));
        }
    }
}
