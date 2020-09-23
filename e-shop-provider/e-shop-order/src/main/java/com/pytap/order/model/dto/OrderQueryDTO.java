package com.pytap.order.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/9/23 10:24
 */
@Data
public class OrderQueryDTO implements Serializable {

    private static final long serialVersionUID = -1921725462125433772L;

    private Long id;

    private Long orderNumber;

    private Long memberId;

    private Long receiverAddressId;

    private BigDecimal freight;

    private BigDecimal totalPrice;

    private Integer payType;

    private String orderSource;

    private Integer status;

    private Integer orderType;

    private Date payTime;

    private Date updateTime;

    private Date createTime;

    private Date startTime;

    private Date endTime;

}
