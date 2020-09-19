package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.EsProductMapper;
import com.pytap.generator.dao.EsShopMapper;
import com.pytap.generator.entity.EsProductExample;
import com.pytap.generator.entity.EsShop;
import com.pytap.generator.entity.EsShopExample;
import com.pytap.product.service.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/9 16:21
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Resource
    private EsShopMapper shopMapper;

    @Resource
    private EsProductMapper productMapper;

    @Override
    public Integer insertShop(EsShop shop) {
        shop.setCreateTime(new Date());
        shop.setCollection(0);
        shop.setHot(0);
        shop.setProductCount(0);
        shop.setRank(0);
        return shopMapper.insert(shop);
    }

    @Transactional
    @Override
    public Integer deleteShopById(Long id) {
        // 删除店铺所有商品
        EsProductExample example = new EsProductExample();
        EsProductExample.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(id);
        productMapper.deleteByExample(example);

        // 删除商铺
        shopMapper.deleteByPrimaryKey(id);

        return 1;
    }

    @Override
    public Integer updateShop(EsShop shop) {
        shop.setUpdateTime(new Date());
        return shopMapper.updateByPrimaryKeyWithBLOBs(shop);
    }

    @Override
    public EsShop getShop(EsShop queryParam) {
        EsShopExample example = new EsShopExample();
        EsShopExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        List<EsShop> list =  shopMapper.selectByExample(example);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public Pager<EsShop> listShops(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        EsShopExample example = new EsShopExample();
        EsShopExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        List<EsShop> list = shopMapper.selectByExampleWithBLOBs(example);
        Pager<EsShop> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(shopMapper.countByExample(example));
        return pager;
    }
}
