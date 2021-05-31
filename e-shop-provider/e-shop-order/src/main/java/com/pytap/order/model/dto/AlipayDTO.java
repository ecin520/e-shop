package com.pytap.order.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ecin520
 * @date 2020/11/2 15:34
 */
@Data
public class AlipayDTO {

    private String orderNo;

    private BigDecimal price;

    private String subject;

    private String body;

}
