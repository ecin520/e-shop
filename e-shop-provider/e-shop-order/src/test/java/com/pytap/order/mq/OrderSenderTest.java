package com.pytap.order.mq;

import com.pytap.generator.entity.EmOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/3 14:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderSenderTest {

    @Resource
    private OrderSender orderSender;

    @Test
    public void testSender() {
        EmOrder order = new EmOrder();
        order.setId(1230L);
        orderSender.send(order);
    }

}