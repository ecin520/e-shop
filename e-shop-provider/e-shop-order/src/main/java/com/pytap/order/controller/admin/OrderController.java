package com.pytap.order.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsOrder;
import com.pytap.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/22 16:57
 */
@RequestMapping("/admin/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @Log(value = "订单参数获取订单分页列表")
    @RequestMapping("/list")
    public ResultEntity<Pager<EsOrder>> listOrders(@RequestBody QueryParam<EsOrder> queryParam) {
        return ResultEntity.success(orderService.listOrders(queryParam));
    }

}
