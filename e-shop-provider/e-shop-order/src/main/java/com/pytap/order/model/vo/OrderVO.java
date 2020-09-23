package com.pytap.order.model.vo;

import com.pytap.generator.entity.EsMember;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsReceiverAddress;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单视图
 * @author Ecin520
 * @date 2020/9/23 11:53
 */
@Data
public class OrderVO implements Serializable {

    private static final long serialVersionUID = -3355083803583445755L;

    private Long id;

    private Long orderNumber;

    private EsMember member;

    private EsReceiverAddress receiverAddress;

    private List<EsProduct> products;

    private BigDecimal freight;

    private BigDecimal totalPrice;

    private Integer payType;

    private String orderSource;

    private Integer status;

    private Integer orderType;

    private Date payTime;

    private Date updateTime;

    private Date createTime;

}
