package com.pytap.order.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsDelivery;
import com.pytap.order.model.dto.DeliveryDTO;
import com.pytap.order.service.DeliveryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/11/4 15:07
 */
@RequestMapping("/admin/delivery")
@RestController
public class DeliveryAdminController {

    @Resource
    private DeliveryService deliveryService;

    @Log(value = "插入物流信息")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertDelivery(@RequestBody EsDelivery delivery) {
        return 1 !=  deliveryService.insertDelivery(delivery) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "更新物流信息")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateDelivery(@RequestBody EsDelivery delivery) {
        return 1 !=  deliveryService.updateDelivery(delivery) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "获取物流信息")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResultEntity<EsDelivery> getDeliveryByParam(@RequestBody EsDelivery queryParam) {
        return ResultEntity.success(deliveryService.getDeliveryByParam(queryParam));
    }

    @Log(value = "添加订单物流信息")
    @RequestMapping(value = "/order/insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertOrderDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        return 1 !=  deliveryService.insertOrderDelivery(deliveryDTO) ? ResultEntity.fail() : ResultEntity.success();
    }



}
