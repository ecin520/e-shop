package com.pytap.sale.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsNewProductRecommend;
import com.pytap.sale.service.NewProductRecommendService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/10/15 16:44
 */
@RestController
@RequestMapping("/admin/new_product_recommend")
public class NewProductAdminController {

    @Resource
    private NewProductRecommendService newProductRecommendService;

    @Log(value = "插入商品推荐")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertNewProductRecommend(@RequestBody EsNewProductRecommend newProductRecommend) {
        return newProductRecommendService.insertNewProductRecommend(newProductRecommend) != 1 ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "删除商品推荐")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteNewProductRecommendById(Long id) {
        return newProductRecommendService.deleteNewProductRecommendById(id) != 1 ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "更新商品推荐")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateNewProductRecommend(@RequestBody EsNewProductRecommend newProductRecommend) {
        return newProductRecommendService.updateNewProductRecommend(newProductRecommend) != 1 ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "获取商品推荐列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultEntity<Pager<EsNewProductRecommend>> listNewProductsRecommend(Integer pageNum, Integer pageSize) {
        return ResultEntity.success(newProductRecommendService.listNewProductsRecommend(pageNum, pageSize));
    }
}
