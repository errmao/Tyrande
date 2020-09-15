package org.tyrande.basemq.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author you.jiu
 * @date 2020/9/15 11:12
 */
@Slf4j
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "tyrande"))
public class TyrandeListener {

    @RabbitHandler
    public void receive(String message) {
        log.info("[消息内容]：{}", message);
    }
}
