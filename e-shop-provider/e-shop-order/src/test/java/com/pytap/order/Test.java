package com.pytap.order;

import com.pytap.common.utils.UUIDUtil;
import com.pytap.generator.entity.EsOrder;
import com.pytap.order.model.dto.OrderParamDTO;
import org.springframework.beans.BeanUtils;

/**
 * @author Ecin520
 * @date 2020/10/31 10:08
 */
public class Test {
    public static void main(String[] args) {
        OrderParamDTO paramDTO = new OrderParamDTO();
        paramDTO.setMemberId(1000L);
        paramDTO.setNote("备注");

        EsOrder order = new EsOrder();
        BeanUtils.copyProperties(paramDTO, order);

        order.setOrderNumber(UUIDUtil.uuidNumberString());

        BeanUtils.copyProperties(order, paramDTO);

        System.out.println(paramDTO.toString());
    }
}
