package com.pytap.product.model.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/10/10 10:24
 */
@Data
@Document(indexName = "product", type = "search_user")
public class SearchProduct implements Serializable {

    private static final long serialVersionUID = 1997528405462573573L;

    @Id
    private Long id;

    private Long productCategoryDetailId;

    private Long shopId;

    @Field(type = FieldType.Keyword)
    private String productCategoryDetailName;

    @Field(type = FieldType.Keyword)
    private String shopName;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
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

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String subTitle;

    private String unit;

    private Integer preStatus;

    private Date saleTime;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String keywords;

    private Date updateTime;

    private Date createTime;

    private String description;

    private String detail;

}
