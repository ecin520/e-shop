package com.pytap.product.service;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.EsProductSpec;
import com.pytap.generator.entity.EsProductSpecDetail;

/**
 * @author Ecin520
 * @date 2020/9/9 16:22
 */
public interface ProductSpecService {
    /**
     * 插入规格
     * @param productSpec 规格
     * @return Integer
     * */
    Integer insertProductSpec(EsProductSpec productSpec);

    /**
     * 主键删除规格
     * @param id 规格主键
     * @return Integer
     * */
    Integer deleteProductSpecById(Long id);

    /**
     * 更新规格
     * @param productSpec 规格
     * @return Integer
     * */
    Integer updateProductSpec(EsProductSpec productSpec);

    /**
     * 获取规格
     * @param queryParam 查询参数
     * @return EsProductSpec
     * */
    EsProductSpec getProductSpec(EsProductSpec queryParam);

    /**
     * 列取规格
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @param queryParam 搜索关键字
     * @return Page<EsProductSpec>
     * */
    Pager<EsProductSpec> listProductSpecs(Integer pageNum, Integer pageSize, EsProductSpec queryParam);

    /**
     * 插入规格详情
     * @param productSpecDetail 规格详情
     * @return Integer
     * */
    Integer insertProductSpecDetail(EsProductSpecDetail productSpecDetail);

    /**
     * 主键删除规格详情
     * @param id 规格详情主键
     * @return Integer
     * */
    Integer deleteProductSpecDetailById(Long id);

    /**
     * 更新规格详情
     * @param productSpecDetail 规格详情
     * @return Integer
     * */
    Integer updateProductSpecDetail(EsProductSpecDetail productSpecDetail);

    /**
     * 获取规格详情
     * @param queryParam 查询参数
     * @return EsProductSpecDetail
     * */
    EsProductSpecDetail getProductSpecDetail(EsProductSpecDetail queryParam);

    /**
     * 列取规格详情
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @param productSpecId 规格id
     * @return Page<EsProductSpecDetail>
     * */
    Pager<EsProductSpecDetail> listProductSpecDetails(Integer pageNum, Integer pageSize, Long productSpecId);


}
