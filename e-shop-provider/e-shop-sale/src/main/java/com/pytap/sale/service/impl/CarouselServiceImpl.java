package com.pytap.sale.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.constant.HomeNumber;
import com.pytap.common.utils.ObjectUtil;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.generator.dao.EsProductCarouselMapper;
import com.pytap.generator.entity.EsProductCarousel;
import com.pytap.generator.entity.EsProductCarouselExample;
import com.pytap.sale.service.CarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/20 10:13
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private EsProductCarouselMapper productCarouselMapper;

    @Override
    public Integer insertCarousel(EsProductCarousel productCarousel) {
        productCarousel.setCreateTime(new Date());
        return productCarouselMapper.insert(productCarousel);
    }

    @Override
    public Integer deleteProductCarouselByParam(EsProductCarousel param) {

        EsProductCarouselExample example = new EsProductCarouselExample();
        EsProductCarouselExample.Criteria criteria = example.createCriteria();

        if (null != param.getId()) {
            criteria.andIdEqualTo(param.getId());
        }
        if (null != param.getProductId()) {
            criteria.andProductIdEqualTo(param.getProductId());
        }

        if (ObjectUtil.isAllNull(param.getId(), param.getProductId())) {
            return 0;
        }

        return productCarouselMapper.deleteByExample(example);
    }

    @Override
    public Integer updateProductCarousel(EsProductCarousel productCarousel) {
        productCarousel.setUpdateTime(new Date());

        EsProductCarouselExample example = new EsProductCarouselExample();
        EsProductCarouselExample.Criteria criteria = example.createCriteria();

        criteria.andProductIdEqualTo(productCarousel.getProductId());

        return productCarouselMapper.updateByExampleSelective(productCarousel, example);
    }

    @Override
    public Pager<EsProductCarousel> listProductCarousels(QueryParam<EsProductCarousel> queryParam) {
        EsProductCarouselExample example = new EsProductCarouselExample();
        example.setOrderByClause("power DESC");

        if (!ObjectUtil.isAllNull(queryParam.getPageNum(), queryParam.getPageSize())) {
            PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());

            List<EsProductCarousel> list = productCarouselMapper.selectByExample(example);

            Pager<EsProductCarousel> pager = new Pager<>();
            pager.setPageNum(queryParam.getPageNum());
            pager.setPageSize(queryParam.getPageSize());
            pager.setContent(list);
            pager.setTotal(productCarouselMapper.countByExample(example));
            return pager;
        } else {
            PageHelper.startPage(0, HomeNumber.HOME_CAROUSEL_COUNT);
            PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());

            List<EsProductCarousel> list = productCarouselMapper.selectByExample(example);

            Pager<EsProductCarousel> pager = new Pager<>();
            pager.setPageNum(0);
            pager.setPageSize(HomeNumber.HOME_CAROUSEL_COUNT);
            pager.setContent(list);
            pager.setTotal(productCarouselMapper.countByExample(example));
            return pager;
        }

    }
}
