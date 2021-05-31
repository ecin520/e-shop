package com.pytap.order.model.dto;

import com.pytap.generator.entity.EsOrderProduct;
import com.pytap.generator.entity.EsProduct;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/26 22:43
 */
@Data
public class OrderParamDTO implements Serializable {

    private static final long serialVersionUID = 4588449327147884413L;

    private Long id;

    private Long memberId;

    private Long shopId;

    private String shopName;

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

    private List<EsOrderProduct> orderProducts;

}
