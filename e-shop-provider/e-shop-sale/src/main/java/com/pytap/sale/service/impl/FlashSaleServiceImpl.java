package com.pytap.sale.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.constant.HomeNumber;
import com.pytap.common.utils.ObjectUtil;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.TimeUtil;
import com.pytap.generator.dao.EsFlashSaleProductMapper;
import com.pytap.generator.entity.EsFlashSaleProduct;
import com.pytap.generator.entity.EsFlashSaleProductExample;
import com.pytap.sale.model.dto.FlashSaleDTO;
import com.pytap.sale.service.FlashSaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/13 11:01
 */
@Service
public class FlashSaleServiceImpl implements FlashSaleService {

    @Resource
    private EsFlashSaleProductMapper flashSaleProductMapper;

    @Override
    public Integer insertFlashSale(EsFlashSaleProduct flashSaleProduct) {
        flashSaleProduct.setCreateTime(new Date());
        return flashSaleProductMapper.insert(flashSaleProduct);
    }

    @Override
    public Integer deleteFlashSaleByParam(FlashSaleDTO param) {
        EsFlashSaleProductExample example = new EsFlashSaleProductExample();
        EsFlashSaleProductExample.Criteria criteria = example.createCriteria();

        if (null != param.getId()) {
            criteria.andIdEqualTo(param.getId());
        }

        if (null != param.getProductId()) {
            criteria.andProductIdEqualTo(param.getProductId());
        }

        if (null != param.getSkuProductId()) {
            criteria.andSkuProductIdEqualTo(param.getSkuProductId());
        }

        if (ObjectUtil.isAllNull(param.getId(), param.getProductId(), param.getSkuProductId())) {
            return 0;
        }

        return flashSaleProductMapper.deleteByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateFlashSale(EsFlashSaleProduct flashSaleProduct) {
        flashSaleProduct.setUpdateTime(new Date());

        EsFlashSaleProductExample example = new EsFlashSaleProductExample();
        EsFlashSaleProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(flashSaleProduct.getProductId());
        criteria.andSkuProductIdEqualTo(flashSaleProduct.getSkuProductId());

        int result = flashSaleProductMapper.updateByExampleSelective(flashSaleProduct, example);

        if (0 == result) {
            result = insertFlashSale(flashSaleProduct);
        }

        return result;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateFlashSales(List<EsFlashSaleProduct> flashSaleProducts) {

        int result = 0;

        // 先删除所有秒杀商品
        if (!flashSaleProducts.isEmpty()) {
            EsFlashSaleProductExample example = new EsFlashSaleProductExample();
            EsFlashSaleProductExample.Criteria criteria = example.createCriteria();
            Long productId = flashSaleProducts.get(0).getProductId();
            if (null != productId) {
                criteria.andProductIdEqualTo(productId);
                flashSaleProductMapper.deleteByExample(example);
            }
        }

        // 增加商品
        for (EsFlashSaleProduct flashSaleProduct : flashSaleProducts) {
            result += flashSaleProductMapper.insert(flashSaleProduct);
        }
        return result;
    }

    @Override
    public List<EsFlashSaleProduct> listFlashSaleProductsByProductId(Long productId) {
        EsFlashSaleProductExample example = new EsFlashSaleProductExample();
        EsFlashSaleProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        return flashSaleProductMapper.selectByExample(example);
    }

    @Override
    public EsFlashSaleProduct getFlashSaleProductByParam(EsFlashSaleProduct queryParam) {
        EsFlashSaleProductExample example = new EsFlashSaleProductExample();
        EsFlashSaleProductExample.Criteria criteria = example.createCriteria();

        if (null != queryParam.getSkuProductId()) {
            criteria.andSkuProductIdEqualTo(queryParam.getSkuProductId());
        }
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }

        // 秒杀时间段的商品才返回
        criteria.andEndTimeGreaterThan(TimeUtil.now()).andStartTimeLessThan(TimeUtil.now());

        List<EsFlashSaleProduct> list = flashSaleProductMapper.selectByExample(example);

        return !list.isEmpty() ? list.get(0) : null;

    }

    @Override
    public Pager<EsFlashSaleProduct> listValidFlashSaleProductsByQueryParam(QueryParam<FlashSaleDTO> queryParam) {
        EsFlashSaleProductExample example = new EsFlashSaleProductExample();
        EsFlashSaleProductExample.Criteria criteria = example.createCriteria();

        // 秒杀时间段内的商品
        criteria.andEndTimeGreaterThan(TimeUtil.now()).andStartTimeLessThan(TimeUtil.now());

        return getFlashSaleProducts(example, queryParam);
    }

    @Override
    public Pager<EsFlashSaleProduct> listFlashSaleProductsByQueryParam(QueryParam<FlashSaleDTO> queryParam) {
        EsFlashSaleProductExample example = new EsFlashSaleProductExample();
        return getFlashSaleProducts(example, queryParam);
    }

    Pager<EsFlashSaleProduct> getFlashSaleProducts(EsFlashSaleProductExample example, QueryParam<FlashSaleDTO> queryParam) {
        if (null != queryParam.getPageNum() && null != queryParam.getPageSize()) {
            PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());
        } else {
            PageHelper.startPage(0, HomeNumber.Home_FLASH_SALE_COUNT);
        }

        List<EsFlashSaleProduct> list = flashSaleProductMapper.selectByExample(example);

        Pager<EsFlashSaleProduct> pager = new Pager<>();
        pager.setPageNum(queryParam.getPageNum());
        pager.setPageSize(queryParam.getPageSize());
        pager.setContent(list);
        pager.setTotal(flashSaleProductMapper.countByExample(example));

        return pager;
    }
}
