package com.pytap.product.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsSkuProduct;
import com.pytap.product.service.ProductService;
import com.pytap.product.service.SkuProductService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Ecin520
 * @date 2020/9/9 20:32
 */
@RequestMapping("/admin/product")
@RestController
public class ProductAdminController {

    @Resource
    private ProductService productService;

    @Resource
    private SkuProductService skuProductService;

    @Log(value = "插入spu商品")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertProduct(@RequestBody EsProduct product) {
        return 1 != productService.insertProduct(product) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "主键删除spu商品")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteProductById(@RequestParam Long id) {
        return 1 != productService.deleteProductById(id) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "更新spu商品")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateProduct(@RequestBody EsProduct product) {
        return 1 != productService.updateProduct(product) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "参数获取商品")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResultEntity<EsProduct> getProduct(@RequestBody EsProduct queryParam) {
        return ResultEntity.success(productService.getProduct(queryParam));
    }

    @Log(value = "参数获取商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultEntity<Pager<EsProduct>> listProducts(@RequestBody QueryParam<EsProduct> queryParam) {
        return ResultEntity.success(productService.listProducts(queryParam.getPageNum(), queryParam.getPageSize(), queryParam.getQueryParam()));
    }

    @Log(value = "插入sku商品")
    @RequestMapping(value = "/sku/insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertSkuProduct(@RequestBody EsSkuProduct skuProduct) {
        return 1 != skuProductService.insertSkuProduct(skuProduct) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "主键删除sku商品")
    @RequestMapping(value = "/sku/delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteSkuProductById(@RequestParam Long id) {
        return 1 != skuProductService.deleteSkuProductById(id) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "更新sku商品")
    @RequestMapping(value = "/sku/update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateSkuProduct(@RequestBody EsSkuProduct skuProduct) {
        return 1 != skuProductService.updateSkuProduct(skuProduct) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "参数获取sku商品")
    @RequestMapping(value = "/sku/query", method = RequestMethod.POST)
    public ResultEntity<EsSkuProduct> getSkuProduct(@RequestBody EsSkuProduct queryParam) {
        return ResultEntity.success(skuProductService.getSkuProduct(queryParam));
    }

    @Log(value = "参数获取sku商品列表")
    @RequestMapping(value = "/sku/list", method = RequestMethod.POST)
    public ResultEntity<Pager<EsSkuProduct>> listSkuProducts(@RequestParam Map<String, String> params) {
        EsSkuProduct queryParam = new EsSkuProduct();
        if (!StringUtils.isEmpty(params.get("name"))) {
            queryParam.setName(params.get("name"));
        }
        if (!StringUtils.isEmpty(params.get("productId"))) {
            queryParam.setProductId(Long.parseLong(params.get("productId")));
        }
        if (!StringUtils.isEmpty(params.get("productSpecDetailId"))) {
            queryParam.setProductSpecDetailId(Long.parseLong(params.get("productSpecDetailId")));
        }
        return ResultEntity.success(skuProductService.listSkuProducts(getPageNum(params), getPageSize(params), queryParam));
    }

    private Integer getPageNum(Map<String, String> params) {
        int pageNum = 1;
        if (!StringUtils.isEmpty(params.get("pageNum"))) {
            pageNum = Integer.parseInt(params.get("pageNum"));
        }
        return pageNum;
    }

    private Integer getPageSize(Map<String, String> params) {
        int pageSize = 10;
        if (!StringUtils.isEmpty(params.get("pageSize"))) {
            pageSize = Integer.parseInt(params.get("pageSize"));
        }
        return pageSize;
    }
}
