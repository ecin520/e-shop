package com.pytap.product.service;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.EsShop;

/**
 * @author Ecin520
 * @date 2020/9/9 16:21
 */
public interface ShopService {
    /**
     * 插入店铺
     * @param shop 店铺
     * @return Integer
     * */
    Integer insertShop(EsShop shop);

    /**
     * 主键删除店铺
     * @param id 店铺主键
     * @return Integer
     * */
    Integer deleteShopById(Long id);

    /**
     * 更新店铺
     * @param shop 店铺
     * @return Integer
     * */
    Integer updateShop(EsShop shop);

    /**
     * 获取店铺
     * @param queryParam 查询参数
     * @return EsShop
     * */
    EsShop getShop(EsShop queryParam);

    /**
     * 列取店铺
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @param keyword 搜索关键字
     * @return Page<EsShop>
     * */
    Pager<EsShop> listShops(Integer pageNum, Integer pageSize, String keyword);
}
