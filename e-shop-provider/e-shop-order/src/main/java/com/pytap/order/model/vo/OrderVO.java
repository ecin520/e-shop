package com.pytap.order.model.vo;

import com.pytap.api.model.vo.MemberVO;
import com.pytap.generator.entity.*;
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

    private MemberVO member;

    private EsShop shop;

    private EsReceiverAddress receiverAddress;

    private EsDelivery delivery;

    private EsCoupon coupon;

    private List<OrderProductVO> products;

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
