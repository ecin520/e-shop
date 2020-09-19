package com.pytap.sale.mq;

import com.pytap.generator.entity.EmOrder;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author Ecin520
 * @date 2020/9/3 15:19
 */
@Component
public class OrderReceiver {

    private static final Logger logger = LoggerFactory.getLogger(OrderReceiver.class);

    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "order-queue", durable = "true"),
                    exchange = @Exchange(name = "order-exchange", type = "topic"),
                    key = "order.#"
            )
    )
    public void onOrderMessage(@Payload EmOrder order, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        logger.info("消息队列消费订单，订单号：{}", order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

}
