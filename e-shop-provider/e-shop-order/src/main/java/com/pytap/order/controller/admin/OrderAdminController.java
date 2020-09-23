package com.pytap.order.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsOrder;
import com.pytap.order.model.dto.OrderQueryDTO;
import com.pytap.order.model.vo.OrderVO;
import com.pytap.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/22 16:57
 */
@RequestMapping("/admin/order")
@RestController
public class OrderAdminController {

    @Resource
    private OrderService orderService;

    @Log(value = "订单参数获取订单分页列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultEntity<Pager<EsOrder>> listOrdersByParam(@RequestBody QueryParam<OrderQueryDTO> queryParam) {
        return ResultEntity.success(orderService.listOrders(queryParam));
    }

    @Log(value = "更新订单")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultEntity<Object> updateOrder(@RequestBody EsOrder order) {
        return 1 != orderService.updateOrder(order) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "通过主键获取订单VO")
    @RequestMapping(value = "/query/{id}", method = RequestMethod.POST)
    public ResultEntity<OrderVO> updateOrder(@PathVariable Long id) {
        return ResultEntity.success(null);
    }

}
