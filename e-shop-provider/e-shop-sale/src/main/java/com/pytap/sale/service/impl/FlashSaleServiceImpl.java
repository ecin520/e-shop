package com.pytap.sale.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.constant.HomeNumber;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.TimeUtil;
import com.pytap.generator.dao.EsFlashSaleProductMapper;
import com.pytap.generator.entity.EsFlashSaleProduct;
import com.pytap.generator.entity.EsFlashSaleProductExample;
import com.pytap.sale.model.dto.FlashSaleDTO;
import com.pytap.sale.service.FlashSaleService;
import org.springframework.stereotype.Service;

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

        return flashSaleProductMapper.deleteByExample(example);
    }

    @Override
    public Integer updateFlashSale(EsFlashSaleProduct flashSaleProduct) {
        flashSaleProduct.setUpdateTime(new Date());
        return flashSaleProductMapper.updateByPrimaryKeySelective(flashSaleProduct);
    }

    @Override
    public Pager<EsFlashSaleProduct> listFlashSaleProductsByQueryParam(QueryParam<FlashSaleDTO> queryParam) {

        EsFlashSaleProductExample example = new EsFlashSaleProductExample();
        EsFlashSaleProductExample.Criteria criteria = example.createCriteria();

        // 秒杀时间段内的商品
        criteria.andEndTimeGreaterThan(TimeUtil.now()).andStartTimeLessThan(TimeUtil.now());

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
