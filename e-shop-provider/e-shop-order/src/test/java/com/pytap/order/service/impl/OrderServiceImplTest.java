package com.pytap.order.service.impl;

import com.pytap.generator.entity.EsOrder;
import com.pytap.order.model.vo.OrderVO;
import com.pytap.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ecin520
 * @date 2020/9/25 0:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceImplTest {

    @Resource
    private OrderService orderService;

    @Test
    void getOrderVO() {
        EsOrder order = new EsOrder();
        order.setId(1000L);
        OrderVO vo = orderService.getOrderVO(order);
        System.out.println(vo.toString());
    }
}