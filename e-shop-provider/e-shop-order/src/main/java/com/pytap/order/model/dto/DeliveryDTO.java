package com.pytap.order.model.dto;

import com.pytap.generator.entity.EsDelivery;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Ecin520
 * @date 2020/11/4 15:46
 */
@Data
public class DeliveryDTO implements Serializable {

    private static final long serialVersionUID = -1005097338802020026L;

    private Long orderId;

    private String orderNumber;

    private EsDelivery delivery;

}
