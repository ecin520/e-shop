package com.pytap.product.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/19 22:51
 */
@Data
public class SkuProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long productId;

    private String name;

    private String showImage;

    private Integer stock;

    private BigDecimal price;

    private Integer sale;

    private List<Long> specDetails;
}
