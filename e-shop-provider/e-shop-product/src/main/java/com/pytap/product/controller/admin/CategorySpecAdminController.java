package com.pytap.product.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProductSpec;
import com.pytap.generator.entity.EsProductSpecDetail;
import com.pytap.product.model.dto.ProductSpecDTO;
import com.pytap.product.service.ProductCategorySpecService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/15 16:51
 */
@RequestMapping("/admin/category_spec")
@RestController
public class CategorySpecAdminController {

    @Resource
    private ProductCategorySpecService productCategorySpecService;

    @Log(value = "批量更新分类规格关系")
    @RequestMapping(value = "/update/{categoryId}", method = RequestMethod.PUT)
    public ResultEntity<Object> updateProductCategorySpecs(@PathVariable Long categoryId, @RequestBody List<Long> productSpecIds) {
        return 1 != productCategorySpecService.updateProductCategorySpecs(categoryId, productSpecIds) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "通过分类id获取对应的规格列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultEntity<List<EsProductSpec>> listProductSpecsByCategoryId(@RequestParam Long categoryId) {
        return ResultEntity.success(productCategorySpecService.listProductSpecsByCategoryId(categoryId));
    }

    @Log(value = "通过分类id获取对应的规格DTO列表")
    @RequestMapping(value = "/dto/list", method = RequestMethod.GET)
    public ResultEntity<List<ProductSpecDTO>> listProductSpecDTOsByCategoryId(@RequestParam Long categoryId) {
        return ResultEntity.success(productCategorySpecService.listProductSpecDTOsByCategoryId(categoryId));
    }

    @Log(value = "通过分类id和商品id获取对应的规格DTO列表")
    @RequestMapping(value = "/dto/list/by/category_product", method = RequestMethod.GET)
    public ResultEntity<List<ProductSpecDTO>> listProductSpecDTOsByCategoryIdAndProductId(@RequestParam Long categoryId, @RequestParam Long productId) {
        return ResultEntity.success(productCategorySpecService.listSpecDTOsByCategoryAndProductId(categoryId, productId));
    }

    @Log(value = "通过和商品id获取对应的规格详情列表")
    @RequestMapping(value = "/spec_detail/list", method = RequestMethod.GET)
    public ResultEntity<List<EsProductSpecDetail>> listSpecDetailsByProductId(@RequestParam Long productId) {
        return ResultEntity.success(productCategorySpecService.listSpecDetailsByProductId(productId));
    }

}
