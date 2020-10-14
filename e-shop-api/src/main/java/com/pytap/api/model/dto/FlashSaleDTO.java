package com.pytap.api.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/10/13 11:38
 */
@Data
public class FlashSaleDTO implements Serializable {

    private static final long serialVersionUID = -6452409114481881761L;

    private Long id;

    private Long productId;

    private BigDecimal flashSalePrice;

    private Integer stockCount;

    private Date startTime;

    private Date endTime;

    private Date updateTime;

    private Date createTime;

}
