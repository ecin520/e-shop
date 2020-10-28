package com.pytap.order.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.common.utils.SecretUtil;
import com.pytap.order.model.dto.OrderParamDTO;
import com.pytap.order.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/10/27 15:03
 */
@RequestMapping("/web/order")
@RestController
public class OrderWebController {

    @Resource
    private OrderService orderService;

    @Log(value = "生成订单")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "generate", method = RequestMethod.POST)
    public ResultEntity<Object> generateOrder(@RequestBody String param) {
        OrderParamDTO order = JSONObject.parseObject(SecretUtil.decrypt(param), OrderParamDTO.class);
        return 1 != orderService.insertOrderByParam(order) ? ResultEntity.fail() : ResultEntity.success();
    }

}
