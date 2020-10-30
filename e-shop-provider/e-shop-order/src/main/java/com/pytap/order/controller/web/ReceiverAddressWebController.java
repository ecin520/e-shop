package com.pytap.order.controller.web;

import com.pytap.common.annotation.Log;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsReceiverAddress;
import com.pytap.order.controller.BaseController;
import com.pytap.order.service.ReceiverAddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/22 16:39
 */
@RequestMapping("/web/receiver_address")
@RestController
public class ReceiverAddressWebController extends BaseController {

    @Resource
    private ReceiverAddressService receiverAddressService;

    @Log(value = "新增收货地址")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertReceiverAddress(@RequestBody EsReceiverAddress receiverAddress) throws GeneralException {
        receiverAddress.setUserId(getUserId());
        return 1 != receiverAddressService.insertReceiverAddress(receiverAddress) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "主键删除收货地址")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteReceiverAddressById(@PathVariable Long id) throws GeneralException {
        Long userId = getUserId();
        return 1 != receiverAddressService.deleteReceiverAddressById(id, userId) ? ResultEntity.fail() : ResultEntity.success();

    }

    @Log(value = "更新收货地址")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateReceiverAddress(@RequestBody EsReceiverAddress receiverAddress) throws GeneralException {
        receiverAddress.setUserId(getUserId());
        return 1 != receiverAddressService.updateReceiverAddress(receiverAddress) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "参数获取收货地址列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultEntity<List<EsReceiverAddress>> listReceiverAddresses(@RequestBody EsReceiverAddress queryParam) throws GeneralException {
        queryParam.setUserId(getUserId());
        return ResultEntity.success(receiverAddressService.listReceiverAddresses(queryParam));
    }
}
