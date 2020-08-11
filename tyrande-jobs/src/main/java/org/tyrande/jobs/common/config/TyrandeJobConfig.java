package org.tyrande.jobs.common.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tyrande.jobs.model.TyrandeJobConfigModel;

import javax.annotation.Resource;

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

    @Resource
    private TyrandeJobConfigModel tyrandeJobConfigModel;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info("[调度任务配置初始化...]");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(tyrandeJobConfigModel.getAppname());
        xxlJobSpringExecutor.setIp(tyrandeJobConfigModel.getIp());
        xxlJobSpringExecutor.setPort(tyrandeJobConfigModel.getPort());
        xxlJobSpringExecutor.setLogPath(tyrandeJobConfigModel.getLogpath());
        xxlJobSpringExecutor.setLogRetentionDays(tyrandeJobConfigModel.getLogretentiondays());
        return xxlJobSpringExecutor;
    }
}
