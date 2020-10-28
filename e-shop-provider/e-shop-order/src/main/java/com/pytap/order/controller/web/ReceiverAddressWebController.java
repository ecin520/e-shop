package com.pytap.order.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.common.utils.SecretUtil;
import com.pytap.generator.entity.EsReceiverAddress;
import com.pytap.order.service.ReceiverAddressService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/22 16:39
 */
@RequestMapping("/web/receiver_address")
@RestController
public class ReceiverAddressWebController {

    @Resource
    private ReceiverAddressService receiverAddressService;

    @Log(value = "新增收货地址")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertReceiverAddress(@RequestBody String param) {
        EsReceiverAddress receiverAddress = JSONObject.parseObject(SecretUtil.decrypt(param), EsReceiverAddress.class);
        return 1 != receiverAddressService.insertReceiverAddress(receiverAddress) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "主键删除收货地址")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultEntity<Object> deleteReceiverAddressById(@RequestBody String param) {
        Long id = JSONObject.parseObject(SecretUtil.decrypt(param), Long.class);
        return 1 != receiverAddressService.deleteReceiverAddressById(id) ? ResultEntity.fail() : ResultEntity.success();

    }

    @Log(value = "更新收货地址")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultEntity<Object> updateReceiverAddress(@RequestBody String param) {
        EsReceiverAddress receiverAddress = JSONObject.parseObject(SecretUtil.decrypt(param), EsReceiverAddress.class);
        return 1 != receiverAddressService.updateReceiverAddress(receiverAddress) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "参数获取收货地址列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultEntity<List<EsReceiverAddress>> listReceiverAddresses(@RequestBody String queryParam) {
        EsReceiverAddress receiverAddress = JSONObject.parseObject(SecretUtil.decrypt(queryParam), EsReceiverAddress.class);
        return ResultEntity.success(receiverAddressService.listReceiverAddresses(receiverAddress));
    }
}
