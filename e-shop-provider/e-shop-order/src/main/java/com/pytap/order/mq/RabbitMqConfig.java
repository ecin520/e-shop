package com.pytap.order.mq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ecin520
 * @date 2020/9/7 10:36
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 绑定交换机
     * */
    @Bean
    DirectExchange orderDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER.getExchange())
                .durable(true)
                .build();
    }
}
