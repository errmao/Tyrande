package org.tyrande.basemq.controller;

import cn.hutool.core.date.DateUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 消息发布
 *
 * @author you.jiu
 * @date 2020/9/15 13:20
 */
@RestController
@RequestMapping("basemq-send")
public class MessageController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("send")
    public void sendMessage() {
        rabbitTemplate.convertAndSend("tyrande", "消息~~~~~~" + DateUtil.now());
    }
}
