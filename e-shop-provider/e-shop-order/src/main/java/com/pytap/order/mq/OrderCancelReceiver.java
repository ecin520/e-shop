package com.pytap.order.mq;

import com.alibaba.fastjson.JSONObject;
import com.pytap.common.constant.OrderStatus;
import com.pytap.common.exception.GeneralException;
import com.pytap.generator.entity.EsOrder;
import com.pytap.order.model.dto.OrderParamDTO;
import com.pytap.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import javax.annotation.Resource;

/**
 * 取消订单消息接收，从死信队列中接收消息
 * @author Ecin520
 * @date 2020/10/28 14:35
 */
@RabbitListener(queues = "dead.letter.queue")
public class OrderCancelReceiver {

    private static final Logger logger = LoggerFactory.getLogger(OrderCancelReceiver.class);

    @Resource
    private OrderService orderService;

    @RabbitHandler
    public void handle(String message) throws GeneralException {
        logger.info("消息超时，已取消订单。{}", message);

        OrderParamDTO dto = JSONObject.parseObject(message, OrderParamDTO.class);

        // 查询订单状态，如果这个订单非待支付状态，则不执行操作
        EsOrder order = new EsOrder();
        order.setId(dto.getId());
        order = orderService.getOrder(order);
        if (!OrderStatus.WAITING_FOR_PAYMENT.equals(order.getStatus())) {
            return;
        }

        // 执行取消订单操作
        orderService.cancelOrder(dto);
    }
}
