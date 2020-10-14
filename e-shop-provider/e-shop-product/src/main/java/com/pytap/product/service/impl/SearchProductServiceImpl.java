package com.pytap.product.service.impl;

import com.pytap.product.dao.ProductDao;
import com.pytap.product.model.domain.SearchProduct;
import com.pytap.product.repository.SearchProductRepository;
import com.pytap.product.service.SearchProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/10 10:42
 */
@Service
public class SearchProductServiceImpl implements SearchProductService {

    private static final Logger logger = LoggerFactory.getLogger(SearchProductServiceImpl.class);

    @Resource
    private SearchProductRepository searchProductRepository;

    @Resource
    private ProductDao productDao;

    @Override
    public Integer importAll() {
        logger.info("开始向es导入商品");
        List<SearchProduct> searchProducts = productDao.listProducts(null);
        if (null != searchProducts && searchProducts.size() > 0) {
            Iterable<SearchProduct> iterable = searchProductRepository.saveAll(searchProducts);
            Iterator<SearchProduct> iterator = iterable.iterator();
            int result = 0;
            while (iterator.hasNext()) {
                result += 1;
                iterator.next();
            }
            logger.info("导入成功，总数量{}件商品", result);
            return result;
        } else {
            logger.error("数据为空，导入失败");
            return 0;
        }
    }

    @Override
    public void delete(Long id) {
        searchProductRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            List<SearchProduct> searchProductList = new ArrayList<>(16);
            for (Long id : ids) {
                SearchProduct searchProduct = new SearchProduct();
                searchProduct.setId(id);
                searchProductList.add(searchProduct);
            }
            searchProductRepository.deleteAll(searchProductList);
        }
    }

    @Override
    public SearchProduct create(Long id) {
        SearchProduct result = null;
        List<SearchProduct> searchProducts = productDao.listProducts(id);
        if (!searchProducts.isEmpty()) {
            SearchProduct searchProduct = searchProducts.get(0);
            result = searchProductRepository.save(searchProduct);
        }
        return result;
    }

    @Override
    public Page<SearchProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return searchProductRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
