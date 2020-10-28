package com.pytap.product.model.vo;

import com.pytap.generator.entity.EsSkuProduct;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/20 15:46
 */
@Data
public class ProductWebVO {
    private Long id;

    private Long productCategoryDetailId;

    private Long shopId;

    private String productCategoryDetailName;

    private String shopName;

    private String name;

    private String parameter;

    private String showImage;

    private String itemNo;

    private Integer deleteStatus;

    private Integer shelfStatus;

    private Integer verifyStatus;

    private BigDecimal price;

    private Integer growthValue;

    private Integer integral;

    private String subTitle;

    private String unit;

    private Integer preStatus;

    private Date saleTime;

    private String keywords;

    private Date updateTime;

    private Date createTime;

    private String description;

    private String detail;

    private List<EsSkuProduct> skuProducts;
}
