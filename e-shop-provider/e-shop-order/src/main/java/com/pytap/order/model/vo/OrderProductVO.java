package com.pytap.order.model.vo;

import com.pytap.generator.entity.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/9/24 16:40
 */
@Data
public class OrderProductVO implements Serializable {

    private static final long serialVersionUID = -1428597316068076511L;

    private Long id;

    private Long orderId;

    private Long skuId;

    private EsSkuProduct skuProduct;

    private EsCoupon coupon;

    private EsDelivery delivery;

    private Integer num;

    private String note;

    private Integer confirmStatus;

    private Date deliveryTime;

    private Date receiveTime;

    private Integer productStatus;

    private Date updateTime;

    private Date createTime;
}
