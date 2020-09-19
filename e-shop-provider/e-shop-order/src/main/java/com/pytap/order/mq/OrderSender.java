package com.pytap.order.mq;

import com.pytap.generator.entity.EmOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/3 14:41
 */
@Component
public class OrderSender {

    private static final String exchange = "order-exchange";

    private static final String routingKey = "order-queue";

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(EmOrder order) {
        rabbitTemplate.convertAndSend(exchange, routingKey, order);
    }

}
