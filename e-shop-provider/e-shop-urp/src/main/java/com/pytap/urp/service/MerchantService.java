package com.pytap.urp.service;

import com.pytap.generator.entity.EsMerchant;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/25 11:00
 */
public interface MerchantService {
    /**
     * 统计总数
     * @return Integer
     * */
    Integer countMerchant();

    /**
     * 插入商家
     * @param merchant 商家实体
     * @return Integer
     * */
    Integer insertMerchant(EsMerchant merchant);

    /**
     * 删除商家
     * @param id 商家id
     * @return Integer
     * */
    Integer deleteMerchantById(Long id);

    /**
     * 更新商家
     * @param merchant 商家实体
     * @return Integer
     * */
    Integer updateMerchantById(EsMerchant merchant);

    /**
     * 查询商家
     * @param queryParam 查询参数
     * @return Merchant 商家实体
     * */
    EsMerchant getMerchant(EsMerchant queryParam);

    /**
     * 获取所有商家
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return List<SysRole>
     * */
    List<EsMerchant> listMerchants(Integer pageNum, Integer pageSize);
}
