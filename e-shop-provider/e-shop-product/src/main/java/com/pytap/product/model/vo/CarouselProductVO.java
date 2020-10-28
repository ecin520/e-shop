package com.pytap.product.model.vo;

import com.pytap.generator.entity.EsProduct;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/10/20 11:03
 */
@Data
public class CarouselProductVO implements Serializable {

    private static final long serialVersionUID = -1925294566661711775L;

    private Long id;

    private Long productId;

    private EsProduct product;

    private String showImage;

    private Integer status;

    private Integer power;

    private Date updateTime;

    private Date createTime;


}
