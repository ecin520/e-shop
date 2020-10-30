package com.pytap.order.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import javax.annotation.Resource;

/**
 * 向延时队列添加消息
 * @author Ecin520
 * @date 2020/10/28 10:01
 */
public class OrderSender {

    @Resource
    private AmqpTemplate amqpTemplate;

    private static final String DELAY_DIRECT_EXCHANGE = "delay.direct.exchange";

    private static final String DELAY_ROUTE_KEY = "delay.route.key";

    private static final Logger logger = LoggerFactory.getLogger(OrderSender.class);

    public void send(String message, Long delayTime) {
        amqpTemplate.convertAndSend(DELAY_DIRECT_EXCHANGE, DELAY_ROUTE_KEY, message, message1 -> {
            message1.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return message1;
        });
        logger.info("延时消息队列添加订单，超时时间为{}", delayTime);
    }

}
