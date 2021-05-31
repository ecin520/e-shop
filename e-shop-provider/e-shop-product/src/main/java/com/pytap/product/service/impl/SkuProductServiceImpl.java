package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.api.model.dto.StockDTO;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.SortUtil;
import com.pytap.generator.dao.EsSkuProductMapper;
import com.pytap.generator.dao.EsSkuSpecDetailMapper;
import com.pytap.generator.entity.EsSkuProduct;
import com.pytap.generator.entity.EsSkuProductExample;
import com.pytap.generator.entity.EsSkuSpecDetail;
import com.pytap.generator.entity.EsSkuSpecDetailExample;
import com.pytap.product.service.SkuProductService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/9 16:24
 */
@Service
public class SkuProductServiceImpl implements SkuProductService {

    @Resource
    private Redisson redisson;

    @Resource
    private EsSkuProductMapper skuProductMapper;

    @Resource
    private EsSkuSpecDetailMapper skuSpecDetailMapper;

    @Override
    public Integer insertSkuProduct(EsSkuProduct skuProduct) {
        skuProduct.setCreateTime(new Date());
        return skuProductMapper.insert(skuProduct);
    }

    @Override
    public Integer deleteSkuProductById(Long id) {
        return skuProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateSkuProduct(EsSkuProduct skuProduct) {
        skuProduct.setUpdateTime(new Date());
        return skuProductMapper.updateByPrimaryKeySelective(skuProduct);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateSkuProductList(List<EsSkuProduct> skuProductList) {
        for (EsSkuProduct skuProduct : skuProductList) {
            skuProduct.setUpdateTime(new Date());
            skuProductMapper.updateByPrimaryKeySelective(skuProduct);
        }
        return 1;
    }

    @Override
    public EsSkuProduct getSkuProduct(EsSkuProduct queryParam) {
        EsSkuProductExample example = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getName()) {
            criteria.andNameEqualTo(queryParam.getName());
        }
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        List<EsSkuProduct> list = skuProductMapper.selectByExample(example);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public Pager<EsSkuProduct> listSkuProducts(QueryParam<EsSkuProduct> queryParam) {
        PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());
        EsSkuProductExample example = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = example.createCriteria();

        if (null != queryParam.getQueryParam()) {

            // sku商品名称模糊搜索
            if (null != queryParam.getQueryParam().getName()) {
                criteria.andNameLike("%" + queryParam.getQueryParam().getName() + "%");
            }

            // 商品spu搜索商品sku
            if (null != queryParam.getQueryParam().getProductId()) {
                criteria.andProductIdEqualTo(queryParam.getQueryParam().getProductId());
            }

        }

        List<EsSkuProduct> list = skuProductMapper.selectByExample(example);
        Pager<EsSkuProduct> pager = new Pager<>();
        pager.setPageNum(queryParam.getPageNum());
        pager.setPageSize(queryParam.getPageSize());
        pager.setContent(list);
        pager.setTotal(skuProductMapper.countByExample(example));
        return pager;
    }

    @Override
    public EsSkuProduct getSkuProductByParam(Long productId, List<Long> specDetailIds) {
        // 通过商品id获取所有的商品sku
        EsSkuProductExample skuProductExample = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = skuProductExample.createCriteria();
        criteria.andProductIdEqualTo(productId);
        List<EsSkuProduct> skuProducts = skuProductMapper.selectByExample(skuProductExample);

        // 通过对比传入的规格详情列表和通过sku_spec_detail表获取的规格详情列表来判断哪个sku才是用户选区的
        for (EsSkuProduct skuProduct : skuProducts) {
            EsSkuSpecDetailExample skuSpecDetailExample = new EsSkuSpecDetailExample();
            EsSkuSpecDetailExample.Criteria criteria1 = skuSpecDetailExample.createCriteria();
            criteria1.andSkuIdEqualTo(skuProduct.getId());
            List<EsSkuSpecDetail> skuSpecDetails = skuSpecDetailMapper.selectByExample(skuSpecDetailExample);
            List<Long> list = new ArrayList<>(16);
            for (EsSkuSpecDetail esSkuSpecDetail : skuSpecDetails) {
                list.add(esSkuSpecDetail.getSpecDetailId());
            }
            if (SortUtil.compareList(list, specDetailIds)) {
                return skuProduct;
            }
        }
        return null;
    }

    @Transactional
    @Override
    public Integer reduceSkuProductStock(StockDTO stockInfo) {

        // 获取商品锁
        RLock lock = redisson.getLock("sku-product-" + stockInfo.getSkuId());
        lock.lock();

        int result = 0;

        try {
            // 取出商品sku
            EsSkuProduct skuProduct = skuProductMapper.selectByPrimaryKey(stockInfo.getSkuId());
            if (null == skuProduct) {
                return 0;
            }
            // 若库存大于所需库存则减去相应的库存
            if (skuProduct.getStock() - stockInfo.getReduceStock() >= 0) {
                skuProduct.setStock(skuProduct.getStock() - stockInfo.getReduceStock());
                result = skuProductMapper.updateByPrimaryKeySelective(skuProduct);
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer increaseSkuProductStock(StockDTO stockDTO) {

        // 获取商品锁
        RLock lock = redisson.getLock("sku-product-" + stockDTO.getSkuId());
        lock.lock();

        int result;

        try {
            EsSkuProduct skuProduct = skuProductMapper.selectByPrimaryKey(stockDTO.getSkuId());
            if (null == skuProduct) {
                return 0;
            }
            skuProduct.setStock(skuProduct.getStock() + stockDTO.getIncreaseStock());
            result = skuProductMapper.updateByPrimaryKeySelective(skuProduct);
        } finally {
            lock.unlock();
        }
        return result;
    }


}
