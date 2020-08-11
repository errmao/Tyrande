package org.tyrande.jobs.common.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 调度配置文件
 *
 * @author Tyrande
 */
@Slf4j
@Configuration
public class TyrandeJobConfig {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info("[调度任务配置初始化...]");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        return xxlJobSpringExecutor;
    }
}
