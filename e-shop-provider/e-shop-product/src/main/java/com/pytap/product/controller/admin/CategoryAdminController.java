package com.pytap.product.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProductCategory;
import com.pytap.generator.entity.EsProductCategoryDetail;
import com.pytap.product.model.vo.ProductCategoryDetailVO;
import com.pytap.product.service.ProductCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/9 16:50
 */
@RequestMapping("/admin/category")
@RestController
public class CategoryAdminController {

    @Resource
    private ProductCategoryService productCategoryService;

    @Log("插入商品分类")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertProductCategory(@RequestBody EsProductCategory productCategory) {
        return 1 != productCategoryService.insertProductCategory(productCategory) ? ResultEntity.fail("插入商品分类失败") : ResultEntity.success("插入商品分类成功", productCategory);
    }

    @Log("列取商品分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultEntity<Pager<EsProductCategory>> listProductCategories(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                                    @RequestParam(value = "keyword") String keyword) {
        return ResultEntity.success(productCategoryService.listProductCategories(pageNum, pageSize, keyword));
    }

    @Log("修改商品分类")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateProductCategory(@RequestBody EsProductCategory productCategory) {
        return 1 != productCategoryService.updateProductCategory(productCategory) ? ResultEntity.fail("更新商品分类失败") : ResultEntity.success("更新商品分类成功");
    }

    @Log("删除商品分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteProductCategoryById(@PathVariable Long id) {
        return 1 != productCategoryService.deleteProductCategoryById(id) ? ResultEntity.fail("更新商品分类失败") : ResultEntity.success("更新商品分类成功");
    }

    @Log("通过商品分类获取详情列表")
    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public ResultEntity<Pager<EsProductCategoryDetail>> listProductCategoryDetailsByParam(@RequestBody QueryParam<EsProductCategoryDetail> queryParam) {
        return ResultEntity.success(productCategoryService.listProductCategoryDetailsByParam(queryParam));
    }

    @Log("添加商品分类详情")
    @RequestMapping(value = "/detail/insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertProductCategoryDetail(@RequestBody EsProductCategoryDetail productCategoryDetail) {
        return 1 != productCategoryService.insertProductCategoryDetail(productCategoryDetail) ? ResultEntity.fail("添加商品分类详情失败") : ResultEntity.success("添加商品分类详情成功");
    }

    @Log("删除商品分类详情")
    @RequestMapping(value = "/detail/delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteProductCategoryDetailById(@RequestParam Long id) {
        return 1 != productCategoryService.deleteProductCategoryDetailById(id) ? ResultEntity.fail("删除商品分类详情失败") : ResultEntity.success("删除商品分类详情成功");
    }

    @Log("更新商品分类详情")
    @RequestMapping(value = "/detail/update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateProductCategoryDetail(@RequestBody EsProductCategoryDetail productCategoryDetail) {
        return 1 != productCategoryService.updateProductCategoryDetail(productCategoryDetail) ? ResultEntity.fail("更新商品分类详情失败") : ResultEntity.success("更新商品分类详情成功");
    }

    @Log("通过分类详情id获取分类详情视图对象")
    @RequestMapping(value = "/detail/{categoryDetailId}", method = RequestMethod.GET)
    public ResultEntity<ProductCategoryDetailVO> getProductCategoryDetailVOById(@PathVariable Long categoryDetailId) {
        return ResultEntity.success(productCategoryService.getProductCategoryDetailVOById(categoryDetailId));
    }

}
