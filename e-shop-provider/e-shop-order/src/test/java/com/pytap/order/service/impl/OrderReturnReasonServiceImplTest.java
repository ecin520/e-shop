package com.pytap.order.service.impl;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.EsOrderReturnReason;
import com.pytap.order.service.OrderReturnReasonService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/24 15:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderReturnReasonServiceImplTest {

    @Resource
    private OrderReturnReasonService orderReturnReasonService;

    @Test
    void getOrderReturnReason() {
        EsOrderReturnReason orderReturnReason = new EsOrderReturnReason();
        orderReturnReason.setId(1000L);
        EsOrderReturnReason result = orderReturnReasonService.getOrderReturnReason(orderReturnReason);
        System.out.println(result);
    }

    @Test
    void listOrderReturnReasons() {
        Pager<EsOrderReturnReason> pager = orderReturnReasonService.listOrderReturnReasons(1, 7);
        System.out.println(pager.toString());
    }
}