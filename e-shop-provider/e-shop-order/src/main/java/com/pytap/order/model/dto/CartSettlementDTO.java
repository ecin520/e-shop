package com.pytap.order.model.dto;

import com.pytap.generator.entity.EsCartProduct;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物车结算dto
 * @author Ecin520
 * @date 2020/11/25 15:16
 */
@Data
public class CartSettlementDTO implements Serializable {

    private static final long serialVersionUID = 4133800994087639732L;

    private Long id;

    private Long shopId;

    private Long skuId;

    private Long memberId;

    private Long couponId;

    private Long productId;

    private String shopName;

    private String productName;

    private String skuName;

    private String skuImage;

    private Integer num;

    private Date updateTime;

    private Date createTime;

}
