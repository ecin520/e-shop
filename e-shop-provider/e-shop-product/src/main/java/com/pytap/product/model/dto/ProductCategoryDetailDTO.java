package com.pytap.product.model.dto;

import com.pytap.generator.entity.EsProductCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/9/20 21:15
 */
@Data
public class ProductCategoryDetailDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer count;

    private String keywords;

    private Integer showStatus;

    private String showImage;

    private Date updateTime;

    private Date createTime;

    private String description;

    private EsProductCategory productCategory;

}
