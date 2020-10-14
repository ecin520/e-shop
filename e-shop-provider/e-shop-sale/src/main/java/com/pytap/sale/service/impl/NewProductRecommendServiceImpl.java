package com.pytap.sale.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.EsNewProductRecommendMapper;
import com.pytap.generator.entity.EsNewProductRecommend;
import com.pytap.sale.service.NewProductRecommendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Pager<EsNewProductRecommend> listNewProductsRecommend(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<EsNewProductRecommend> list = newProductRecommendMapper.selectByExample(null);


        Pager<EsNewProductRecommend> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(newProductRecommendMapper.countByExample(null));

        return pager;
    }
}
