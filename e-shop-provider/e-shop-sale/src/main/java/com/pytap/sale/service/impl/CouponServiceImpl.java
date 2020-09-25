package com.pytap.sale.service.impl;

import com.pytap.generator.dao.EsCouponMapper;
import com.pytap.generator.entity.EsCoupon;
import com.pytap.generator.entity.EsCouponExample;
import com.pytap.sale.service.CouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/25 10:56
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Resource
    private EsCouponMapper couponMapper;

    @Override
    public EsCoupon getCoupon(EsCoupon queryParam) {
        EsCouponExample example = new EsCouponExample();
        EsCouponExample.Criteria criteria = example.createCriteria();

        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }

        List<EsCoupon> list = couponMapper.selectByExample(example);

        return !list.isEmpty() ? list.get(0) : null;
    }
}
