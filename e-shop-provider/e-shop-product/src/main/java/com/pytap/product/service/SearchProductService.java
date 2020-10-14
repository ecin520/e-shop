package com.pytap.product.service;

import com.pytap.product.model.domain.SearchProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/10 10:34
 */
public interface SearchProductService {

    /**
     * 将数据库中的商品导入到es
     * @return Integer
     * */
    Integer importAll();

    /**
     * 删除商品
     * @param id id
     * */
    void delete(Long id);

    /**
     * 批量删除商品
     * @param ids id列表
     * */
    void delete(List<Long> ids);

    /**
     * 根据id向es添加商品
     * @param id 主键
     * @return SearchProduct
     * */
    SearchProduct create(Long id);

    /**
     * 搜索商品
     * @param keyword 关键字
     * @param pageNum 第几页
     * @param pageSize 每页多少数据
     * @return Page<SearchProduct>
     * */
    Page<SearchProduct> search(String keyword, Integer pageNum, Integer pageSize);

}
