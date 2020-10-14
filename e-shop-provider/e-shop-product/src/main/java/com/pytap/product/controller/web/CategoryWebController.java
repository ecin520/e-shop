package com.pytap.product.controller.web;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProductCategory;
import com.pytap.product.service.ProductCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/10 18:31
 */
@RequestMapping("/web/category")
@RestController
public class CategoryWebController {

    @Resource
    private ProductCategoryService productCategoryService;

    @Log("列取首页商品分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultEntity<List<EsProductCategory>> listCategories() {
        return ResultEntity.success(productCategoryService.listProductCategories(0, 0, null).getContent());
    }

    @Log("列取首页商品推荐分类")
    @RequestMapping(value = "/recommend/list", method = RequestMethod.GET)
    public ResultEntity<Pager<EsProductCategory>> listRecommendCategories() {
        return ResultEntity.success(productCategoryService.listProductCategories(0, 0, null));
    }

}
