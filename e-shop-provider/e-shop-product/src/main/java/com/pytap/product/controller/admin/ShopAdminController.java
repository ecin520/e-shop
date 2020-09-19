package com.pytap.product.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsShop;
import com.pytap.product.service.ShopService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/9 20:29
 */
@RequestMapping("/admin/shop")
@RestController
public class ShopAdminController {

    @Resource
    private ShopService shopService;

    @Log("添加店铺")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertShop(@RequestBody EsShop shop) {
        return 1 != shopService.insertShop(shop) ? ResultEntity.fail("插入店铺失败") : ResultEntity.success("插入店铺成功");
    }

    @Log("删除店铺")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteShopById(@RequestParam Long id) {
        return 1 != shopService.deleteShopById(id) ? ResultEntity.fail("删除店铺失败") : ResultEntity.success("删除店铺成功");
    }

    @Log("更新店铺")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateShop(@RequestBody EsShop shop) {
        return 1 != shopService.updateShop(shop) ? ResultEntity.fail("更新店铺失败") : ResultEntity.success("更新店铺成功");
    }

    @Log("店铺参数获取店铺")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResultEntity<EsShop> getShop(@RequestBody EsShop queryParam) {
        return ResultEntity.success(shopService.getShop(queryParam));
    }

    @Log("列取店铺")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultEntity<Pager<EsShop>> listShops(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                 @RequestParam(value = "keyword") String keyword) {
        return ResultEntity.success(shopService.listShops(pageNum, pageSize, keyword));
    }
}
