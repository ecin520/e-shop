package com.pytap.product.model.vo;

import com.pytap.generator.entity.EsProductSpecDetail;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/14 10:34
 */
@Data
public class ProductSpecVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer standard;

    private String description;

    private Date updateTime;

    private Date createTime;

    private List<EsProductSpecDetail> details;

}
