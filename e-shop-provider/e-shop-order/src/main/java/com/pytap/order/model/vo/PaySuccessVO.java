package com.pytap.order.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/11/2 17:56
 */
@Data
public class PaySuccessVO implements Serializable {

    private static final long serialVersionUID = 115022092757038665L;

    private Long id;

    private Long memberId;

    private Long shopId;

    private Long receiverAddressId;

    private Long deliveryId;

    private Long couponId;

    private String orderNumber;

    private BigDecimal freight;

    private BigDecimal totalPrice;

    private Integer payType;

    private String paySerial;

    private String orderSource;

    private Integer confirmStatus;

    private Date deliveryTime;

    private Date receiveTime;

    private String note;

    private Integer status;

    private Integer orderType;

    private Date payTime;

    private Date updateTime;

    private Date createTime;
}
