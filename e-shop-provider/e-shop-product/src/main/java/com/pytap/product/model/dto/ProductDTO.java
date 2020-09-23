package com.pytap.product.model.dto;

import com.pytap.generator.entity.EsProduct;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品传输对象
 * @author Ecin520
 * @date 2020/9/19 22:28
 */
@Data
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private EsProduct product;

    private List<SkuProductDTO> skuProductList;

}
