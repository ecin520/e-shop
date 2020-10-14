package com.pytap.product.repository;

import com.pytap.product.model.domain.SearchProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * es提供的jpa接口
 * @author Ecin520
 * @date 2020/10/10 10:29
 */
public interface SearchProductRepository extends ElasticsearchRepository<SearchProduct, Long> {
    Page<SearchProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
