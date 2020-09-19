package com.pytap.product.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProductSpec;
import com.pytap.generator.entity.EsProductSpecDetail;
import com.pytap.product.service.ProductSpecService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/9 20:31
 */
@RequestMapping("/admin/spec")
@RestController
public class SpecAdminController {

    @Resource
    private ProductSpecService productSpecService;

    @Log(value = "插入商品规格")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertProductSpec(@RequestBody EsProductSpec productSpec) {
        return 1 != productSpecService.insertProductSpec(productSpec) ? ResultEntity.fail(): ResultEntity.success();
    }

    @Log(value = "主键删除商品规格")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteProductSpecById(@RequestParam Long id) {
        return 1 != productSpecService.deleteProductSpecById(id) ? ResultEntity.fail(): ResultEntity.success();
    }

    @Log(value = "更新商品规格")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateProductSpec(@RequestBody EsProductSpec productSpec) {
        return 1 != productSpecService.updateProductSpec(productSpec) ? ResultEntity.fail(): ResultEntity.success();
    }

    @Log(value = "商品规格参数获取商品规格")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResultEntity<EsProductSpec> getProductSpec(@RequestBody EsProductSpec queryParam) {
        return ResultEntity.success(productSpecService.getProductSpec(queryParam));
    }

    @Log(value = "获取商品规格列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultEntity<Pager<EsProductSpec>> listProductSpecs(@RequestBody QueryParam<EsProductSpec> queryParam) {
        return ResultEntity.success(productSpecService.listProductSpecs(queryParam.getPageNum(), queryParam.getPageSize(), queryParam.getQueryParam()));
    }

    @Log(value = "插入商品规格详情")
    @RequestMapping(value = "/detail/insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertProductSpecDetail(@RequestBody EsProductSpecDetail productSpecDetail) {
        return 1 != productSpecService.insertProductSpecDetail(productSpecDetail) ? ResultEntity.fail(): ResultEntity.success(productSpecDetail);
    }

    @Log(value = "删除商品规格详情")
    @RequestMapping(value = "/detail/delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteProductSpecDetailById(@RequestParam Long id) {
        return 1 != productSpecService.deleteProductSpecDetailById(id) ? ResultEntity.fail(): ResultEntity.success();
    }

    @Log(value = "更新商品规格详情")
    @RequestMapping(value = "/detail/update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateProductSpecDetail(@RequestBody EsProductSpecDetail productSpecDetail) {
        return 1 != productSpecService.updateProductSpecDetail(productSpecDetail) ? ResultEntity.fail(): ResultEntity.success();
    }

    @Log(value = "商品规格详情参数查询商品规格详情")
    @RequestMapping(value = "/detail/query", method = RequestMethod.POST)
    public ResultEntity<EsProductSpecDetail> getProductSpecDetail(@RequestBody EsProductSpecDetail queryParam) {
        return ResultEntity.success(productSpecService.getProductSpecDetail(queryParam));
    }

    @Log(value = "通过商品规格id获取商品规格详情列表")
    @RequestMapping(value = "/detail/list", method = RequestMethod.GET)
    public ResultEntity<Pager<EsProductSpecDetail>> listProductSpecDetails(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                                           @RequestParam(value = "productSpecId") Long productSpecId) {
        return ResultEntity.success(productSpecService.listProductSpecDetails(pageNum, pageSize, productSpecId));
    }

}
