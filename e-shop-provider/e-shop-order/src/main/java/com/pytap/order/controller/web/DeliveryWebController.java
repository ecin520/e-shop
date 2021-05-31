package com.pytap.order.controller.web;

import com.pytap.common.annotation.Log;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.ResultEntity;
import com.pytap.order.controller.BaseController;
import com.pytap.order.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/12/31 10:25
 */
@RestController
@RequestMapping("/web/delivery")
public class DeliveryWebController extends BaseController {

    @Resource
    private DeliveryService deliveryService;

    @Log("物流确认收货")
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ResultEntity<Object> confirmReceipt(@RequestParam String deliveryNumber, @RequestParam Long orderId, @RequestParam Long userId) throws GeneralException {
        checkLegalityById(userId);
        return 1 != deliveryService.confirmReceipt(deliveryNumber, orderId) ? ResultEntity.fail() : ResultEntity.success();
    }

}
