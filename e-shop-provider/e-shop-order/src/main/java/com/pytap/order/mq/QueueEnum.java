package com.pytap.order.mq;

/**
 * 消息队列枚举类
 * @author Ecin520
 * @date 2020/9/7 10:22
 */
public enum QueueEnum {

    QUEUE_ORDER("order-exchange", "order", "");

    private final String exchange;
    private final String name;
    private final String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }

    public String getExchange() {
        return exchange;
    }

    public String getName() {
        return name;
    }

    public String getRouteKey() {
        return routeKey;
    }
}
