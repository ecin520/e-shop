package com.pytap.product.model.vo;

import com.pytap.generator.entity.EsProduct;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/10/15 17:08
 */
@Data
public class NewProductRecommendVO implements Serializable {

    private static final long serialVersionUID = 5811663941820022327L;

    private Long id;

    private Long productId;

    private EsProduct product;

    private String productName;

    private Integer status;

    private Integer power;

    private Date updateTime;

    private Date createTime;
}
