package com.pytap.product.controller.web;

import com.pytap.api.model.dto.FlashSaleDTO;
import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsSkuProduct;
import com.pytap.product.model.domain.SearchProduct;
import com.pytap.product.model.vo.CarouselProductVO;
import com.pytap.product.model.vo.FlashSaleProductVO;
import com.pytap.product.model.vo.ProductWebVO;
import com.pytap.product.service.HomeProductService;
import com.pytap.product.service.ProductService;
import com.pytap.product.service.SearchProductService;
import com.pytap.product.service.SkuProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/10 18:24
 */
@RequestMapping("/web/product")
@RestController
public class ProductWebController {

    @Resource
    private SearchProductService searchProductService;

    @Resource
    private ProductService productService;

    @Resource
    private SkuProductService skuProductService;

    @Resource
    private HomeProductService homeProductService;

    @Log(value = "搜索商品")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResultEntity<Page<SearchProduct>> searchProduct(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                           @RequestParam(value = "keyword") String keyword) {
        return ResultEntity.success(searchProductService.search(keyword, pageNum, pageSize));
    }


    @Log(value = "获取秒杀商品")
    @RequestMapping(value = "/flash_sale/list", method = RequestMethod.POST)
    public ResultEntity<Pager<FlashSaleProductVO>> listHomeFlashSaleProducts(@RequestBody QueryParam<FlashSaleDTO> queryParam) {
        return ResultEntity.success(homeProductService.listHomeFlashSaleProducts(queryParam));
    }

    @Log(value = "获取新品上市商品")
    @RequestMapping(value = "/new/list", method = RequestMethod.GET)
    public ResultEntity<Pager<EsProduct>> listNewProducts(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize) {
        return ResultEntity.success(homeProductService.listHomeNewProducts(pageNum, pageSize));
    }

    @Log(value = "获取推荐商品")
    @RequestMapping(value = "/recommend/list", method = RequestMethod.GET)
    public ResultEntity<Pager<EsProduct>> listRecommendProducts(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                                                @RequestParam(value = "pageSize", defaultValue = "12") Integer pageSize) {
        return ResultEntity.success(homeProductService.listHomeRecommendProducts(pageNum, pageSize));
    }

    @Log(value = "获取轮播图商品")
    @RequestMapping(value = "/carousel/list", method = RequestMethod.GET)
    public ResultEntity<Pager<CarouselProductVO>> listCarouselProducts() {
        return ResultEntity.success(homeProductService.listHomeCarouselProducts());
    }

    @Log(value = "参数获取指定商品信息")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResultEntity<EsProduct> getProductByParam(@RequestBody EsProduct queryParam) {
        return ResultEntity.success(productService.getProduct(queryParam));
    }

    @Log(value = "商品id获取指定商品视图")
    @RequestMapping(value = "/view/query", method = RequestMethod.POST)
    public ResultEntity<ProductWebVO> getProductWebVO(@RequestBody EsProduct queryParam) {
        return ResultEntity.success(productService.getProductWebVO(queryParam));
    }

    @Log(value = "通过规格id列表和商品id获取sku")
    @RequestMapping(value = "/sku/{productId}", method = RequestMethod.POST)
    public ResultEntity<EsSkuProduct> getSkuProductByParam(@PathVariable Long productId, @RequestBody List<Long> specDetailIds) {
        return ResultEntity.success(skuProductService.getSkuProductByParam(productId, specDetailIds));
    }

    @Log(value = "通过sku的id获取sku")
    @RequestMapping(value = "/sku/{id}", method = RequestMethod.GET)
    public ResultEntity<EsSkuProduct> getSkuProductById(@PathVariable Long id) {
        EsSkuProduct skuProduct = new EsSkuProduct();
        skuProduct.setId(id);
        return ResultEntity.success(skuProductService.getSkuProduct(skuProduct));
    }

}
