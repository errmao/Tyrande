package org.turande.basemq.common.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.turande.basemq.common.constant.RabbitMqEnum;

/**
 * 消息中间件配置类
 *
 * @author you.jiu
 * @date 2020/9/15 10:24
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queueWork() {
        return new Queue(RabbitMqEnum.TYRANDE_WORK.value());
    }
}
