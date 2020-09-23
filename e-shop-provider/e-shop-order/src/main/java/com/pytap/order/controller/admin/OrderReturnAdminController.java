package com.pytap.order.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsOrderReturnReason;
import com.pytap.order.service.OrderReturnReasonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/23 17:52
 */
@RequestMapping("/admin/order/return")
@RestController
public class OrderReturnAdminController {

    @Resource
    private OrderReturnReasonService orderReturnReasonService;

    @Log(value = "插入退货原因")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertOrderReturnReason(@RequestBody EsOrderReturnReason orderReturnReason) {
        return 1 != orderReturnReasonService.insertOrderReturnReason(orderReturnReason) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "删除退货原因")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteOrderReturnReasonById(@PathVariable Long id) {
        return 1 != orderReturnReasonService.deleteOrderReturnReasonById(id) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "更新退货原因")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateOrderReturnReason(@RequestBody EsOrderReturnReason orderReturnReason) {
        return 1 != orderReturnReasonService.updateOrderReturnReason(orderReturnReason) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "获取退货原因列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultEntity<Pager<EsOrderReturnReason>> listOrderReturnReasons(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        return ResultEntity.success(orderReturnReasonService.listOrderReturnReasons(pageNum, pageSize));
    }
}
