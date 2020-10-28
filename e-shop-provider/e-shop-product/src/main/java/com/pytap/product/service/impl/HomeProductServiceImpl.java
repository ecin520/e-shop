package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.api.model.dto.FlashSaleDTO;
import com.pytap.api.service.api.sale.NewProductRecommendFeignService;
import com.pytap.common.constant.HomeNumber;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.dao.EsProductMapper;
import com.pytap.generator.entity.*;
import com.pytap.product.model.vo.CarouselProductVO;
import com.pytap.product.model.vo.FlashSaleProductVO;
import com.pytap.product.service.HomeProductService;
import com.pytap.product.service.ProductCategoryService;
import com.pytap.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/13 10:36
 */
@Service
public class HomeProductServiceImpl implements HomeProductService {

    private static final Logger logger = LoggerFactory.getLogger(HomeProductServiceImpl.class);

    @Resource
    private ProductService productService;

    @Resource
    private EsProductMapper productMapper;

    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private NewProductRecommendFeignService newProductRecommendFeignService;

    @Override
    public Pager<FlashSaleProductVO> listHomeFlashSaleProducts(QueryParam<FlashSaleDTO> queryParam) {
        return productService.listValidFlashSaleProducts(queryParam);
    }

    @Override
    public Pager<EsProduct> listHomeNewProducts(Integer pageNum, Integer pageSize) {
        Pager<EsProduct> pager = new Pager<>();
        List<EsProduct> products = new ArrayList<>(16);

        ResultEntity<Pager<EsNewProductRecommend>> resultEntity = newProductRecommendFeignService.listNewProductsRecommend(pageNum, pageSize);

        if (200 == resultEntity.getCode()) {
            if (null != resultEntity.getData()) {
                if (null != resultEntity.getData().getContent()) {
                    List<EsNewProductRecommend> list = resultEntity.getData().getContent();
                    for (EsNewProductRecommend newProductRecommend : list) {
                        EsProduct product = new EsProduct();
                        product.setId(newProductRecommend.getProductId());
                        products.add(productService.getProduct(product));
                    }
                    pager.setTotal(resultEntity.getData().getTotal());
                    pager.setPageNum(resultEntity.getData().getPageNum());
                    pager.setPageSize(resultEntity.getData().getPageSize());
                    pager.setContent(products);
                    return pager;
                }
            }
        } else {
            logger.error(resultEntity.getMessage());
        }
        return null;
    }

    /**
     * 在这里随机选出商品，先不写推荐算法
     * */
    @Override
    public Pager<EsProduct> listHomeRecommendProducts(Integer pageNum, Integer pageSize) {

        Pager<EsProduct> pager = new Pager<>();

        EsProductExample example = new EsProductExample();

        PageHelper.startPage(pageNum, pageSize);
        List<EsProduct> products = productMapper.selectByExample(example);
        pager.setTotal(productMapper.countByExample(null));
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(products);

        return pager;
    }

    @Override
    public Pager<CarouselProductVO> listHomeCarouselProducts() {
        QueryParam<EsProductCarousel> queryParam = new QueryParam<>();
        queryParam.setPageNum(0);
        queryParam.setPageSize(HomeNumber.HOME_CAROUSEL_COUNT);
        queryParam.setQueryParam(null);
        return productService.listCarouselProducts(queryParam);
    }

    @Override
    public Pager<EsProductCategory> listHomeFeaturedCategories(Integer pageNum, Integer pageSize) {
        return productCategoryService.listProductCategories(pageNum, pageSize, "recommend");
    }

}
