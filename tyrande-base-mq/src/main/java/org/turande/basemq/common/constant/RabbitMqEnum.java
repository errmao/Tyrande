package org.turande.basemq.common.constant;

/**
 * 消息中间件枚举
 *
 * @author you.jiu
 * @date 2020/9/15 10:36
 */
public enum RabbitMqEnum {

    TYRANDE_WORK("tyrande_work");

    private String value;

    /**
     * 构造方法
     */
    private RabbitMqEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
