package com.pytap.product.dao;

import com.pytap.product.model.domain.SearchProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/10 10:46
 */
@Mapper
public interface ProductDao {
    /**
     * 获取所有商品
     * @return List<SearchProduct>
     * */
    List<SearchProduct> listProducts(@Param("id") Long id);
}
