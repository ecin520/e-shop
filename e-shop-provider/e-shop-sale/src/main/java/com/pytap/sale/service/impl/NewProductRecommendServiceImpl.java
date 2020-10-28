package com.pytap.sale.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.EsNewProductRecommendMapper;
import com.pytap.generator.entity.EsNewProductRecommend;
import com.pytap.generator.entity.EsNewProductRecommendExample;
import com.pytap.sale.service.NewProductRecommendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/13 11:57
 */
@Service
public class NewProductRecommendServiceImpl implements NewProductRecommendService {

    @Resource
    private EsNewProductRecommendMapper newProductRecommendMapper;

    @Override
    public Integer insertNewProductRecommend(EsNewProductRecommend newProductRecommend) {
        newProductRecommend.setCreateTime(new Date());
        return newProductRecommendMapper.insert(newProductRecommend);
    }

    @Override
    public Integer deleteNewProductRecommendById(Long id) {
        return newProductRecommendMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateNewProductRecommend(EsNewProductRecommend newProductRecommend) {
        newProductRecommend.setUpdateTime(new Date());

        EsNewProductRecommendExample example = new EsNewProductRecommendExample();
        EsNewProductRecommendExample.Criteria criteria = example.createCriteria();

        criteria.andProductIdEqualTo(newProductRecommend.getProductId());

        return newProductRecommendMapper.updateByExampleSelective(newProductRecommend, example);
    }

    @Override
    public Pager<EsNewProductRecommend> listNewProductsRecommend(Integer pageNum, Integer pageSize) {
        EsNewProductRecommendExample example = new EsNewProductRecommendExample();
        example.setOrderByClause("power DESC");

        PageHelper.startPage(pageNum, pageSize);
        List<EsNewProductRecommend> list = newProductRecommendMapper.selectByExample(example);

        Pager<EsNewProductRecommend> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(newProductRecommendMapper.countByExample(null));

        return pager;
    }
}
