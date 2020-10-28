package com.pytap.product.model.vo;

import com.pytap.generator.entity.EsFlashSaleProduct;
import com.pytap.generator.entity.EsSkuProduct;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/10/15 15:03
 */
@Data
public class FlashSaleProductVO implements Serializable {

    private static final long serialVersionUID = -2193739421147189040L;

    private Long id;

    private Long productId;

    private Long skuProductId;

    private ProductVO product;

    private EsSkuProduct skuProduct;

    private BigDecimal flashSalePrice;

    private Integer stockCount;

    private Date startTime;

    private Date endTime;

    private Date updateTime;

    private Date createTime;

}
