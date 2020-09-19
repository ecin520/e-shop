package com.pytap.urp.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.generator.dao.EsMerchantMapper;
import com.pytap.generator.entity.EsMerchant;
import com.pytap.generator.entity.EsMerchantExample;
import com.pytap.urp.service.MerchantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/25 11:04
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Resource
    private EsMerchantMapper emMerchantMapper;

    @Override
    public Integer countMerchant() {
        return emMerchantMapper.countByExample(null);
    }

    @Override
    public Integer insertMerchant(EsMerchant merchant) {
        merchant.setCreateTime(new Date());
        return emMerchantMapper.insert(merchant);
    }

    @Override
    public Integer deleteMerchantById(Long id) {
        return emMerchantMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateMerchantById(EsMerchant merchant) {
        merchant.setUpdateTime(new Date());
        return emMerchantMapper.updateByPrimaryKey(merchant);
    }

    @Override
    public EsMerchant getMerchant(EsMerchant queryParam) {

        EsMerchantExample example = new EsMerchantExample();
        EsMerchantExample.Criteria criteria = example.createCriteria();

        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getUserId());
        }
        if (null != queryParam.getShopId()) {
            criteria.andShopIdEqualTo(queryParam.getShopId());
        }
        if (null != queryParam.getUsername()) {
            criteria.andUsernameEqualTo(queryParam.getUsername());
        }
        if (null != queryParam.getStatus()) {
            criteria.andStatusEqualTo(queryParam.getStatus());
        }

        List<EsMerchant> list = emMerchantMapper.selectByExample(example);

        return list.size() != 0 ? list.get(0) : null;
    }

    @Override
    public List<EsMerchant> listMerchants(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return emMerchantMapper.selectByExample(null);
    }

}
