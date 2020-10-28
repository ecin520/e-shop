package com.pytap.order.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 添加订单成功后，将订单信息放到延时队列中，
 * @author Ecin520
 * @date 2020/9/7 10:36
 */
@Configuration
public class RabbitMqConfig {

    private static final String DEAD_LETTER_DIRECT_EXCHANGE = "dead.letter.direct.exchange";

    private static final String DELAY_DIRECT_EXCHANGE = "delay.direct.exchange";

    private static final String DEAD_LETTER_ROUTE_KEY = "dead.letter.route.key";

    private static final String DELAY_ROUTE_KEY = "delay.route.key";

    private static final String DEAD_LETTER_QUEUE = "dead.letter.queue";

    private static final String DELAY_QUEUE = "delay.queue";

    /**
     * 死信队列绑定的交换机
     * */
    @Bean
    public DirectExchange deadLetterDirectExchange() {
        return new DirectExchange(DEAD_LETTER_DIRECT_EXCHANGE);
    }

    /**
     * 延迟队列绑定的交换机
     * */
    @Bean
    public DirectExchange delayDirectExchange() {
        return new DirectExchange(DELAY_DIRECT_EXCHANGE);
    }

    /**
     * 死信队列
     * */
    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE);
    }

    /**
     * 延时队列，到期后转发的交换机与路由键，也就是转发到死信队列
     * */
    @Bean
    public Queue delayQueue() {
        return QueueBuilder.durable(DELAY_QUEUE)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_DIRECT_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_ROUTE_KEY)
                .build();
    }

    /**
     * 将死信队列绑定到死信交换机，并绑定死信路由键
     * */
    @Bean
    public Binding directBinding(DirectExchange deadLetterDirectExchange, Queue deadLetterQueue) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterDirectExchange).with(DEAD_LETTER_ROUTE_KEY);
    }

    /**
     * 将延时队列绑定到延时交换机，并绑定延时路由键
     * */
    @Bean
    public Binding delayDirectBinding(DirectExchange delayDirectExchange, Queue delayQueue) {
        return BindingBuilder.bind(delayQueue).to(delayDirectExchange).with(DELAY_ROUTE_KEY);
    }

    @Bean
    public OrderSender orderSender() {
        return new OrderSender();
    }

    @Bean
    public OrderCancelReceiver orderCancelReceiver() {
        return new OrderCancelReceiver();
    }


}
