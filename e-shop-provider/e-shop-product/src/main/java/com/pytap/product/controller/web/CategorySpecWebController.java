package com.pytap.product.controller.web;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.product.model.vo.ProductSpecVO;
import com.pytap.product.service.ProductCategorySpecService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/21 9:36
 */
@RequestMapping("/web/category_spec")
@RestController
public class CategorySpecWebController {

    @Resource
    private ProductCategorySpecService productCategorySpecService;

    @Log(value = "通过分类详情id和商品id获取对应的规格视图列表")
    @RequestMapping(value = "/view/list/by/category_detail_product", method = RequestMethod.GET)
    public ResultEntity<List<ProductSpecVO>> listSpecVOsByCategoryDetailAndProductId(@RequestParam Long categoryDetailId, @RequestParam Long productId) {
        return ResultEntity.success(productCategorySpecService.listSpecVOsByCategoryDetailAndProductId(categoryDetailId, productId));
    }

}
