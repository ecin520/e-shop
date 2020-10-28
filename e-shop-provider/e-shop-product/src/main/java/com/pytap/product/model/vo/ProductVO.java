package com.pytap.product.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/10/15 16:22
 */
@Data
public class ProductVO {
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

    private Integer stock;
}
