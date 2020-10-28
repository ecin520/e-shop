package com.pytap.order.service.impl;

import com.pytap.common.utils.ObjectUtil;
import com.pytap.generator.dao.EsCartProductMapper;
import com.pytap.generator.entity.EsCartProduct;
import com.pytap.generator.entity.EsCartProductExample;
import com.pytap.order.service.CartProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/26 17:36
 */
@Service
public class CartProductServiceImpl implements CartProductService {

    @Resource
    private EsCartProductMapper cartProductMapper;

    @Override
    public Integer insertCartProduct(EsCartProduct cartProduct) {
        return cartProductMapper.insert(cartProduct);
    }

    @Override
    public Integer deleteCartProduct(EsCartProduct param) {
        EsCartProductExample example = new EsCartProductExample();
        EsCartProductExample.Criteria criteria = example.createCriteria();

        if (ObjectUtil.isAllNull(param.getId(), param.getMemberId())) {
            return 0;
        }
        if (null != param.getId()) {
            criteria.andIdEqualTo(param.getId());
        }

        if (null != param.getMemberId()) {
            criteria.andMemberIdEqualTo(param.getMemberId());
        }

        return cartProductMapper.deleteByExample(example);
    }

    @Override
    public Integer updateCartProduct(EsCartProduct cartProduct) {
        return cartProductMapper.updateByPrimaryKeySelective(cartProduct);
    }

    @Override
    public List<EsCartProduct> listCartProducts(EsCartProduct queryParam) {
        EsCartProductExample example = new EsCartProductExample();
        EsCartProductExample.Criteria criteria = example.createCriteria();

        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }

        if (null != queryParam.getMemberId()) {
            criteria.andMemberIdEqualTo(queryParam.getMemberId());
        }

        return cartProductMapper.selectByExample(example);
    }
}
