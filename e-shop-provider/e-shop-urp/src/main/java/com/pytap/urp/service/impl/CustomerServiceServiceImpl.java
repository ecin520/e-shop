package com.pytap.urp.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.generator.dao.EsCustomerServiceMapper;
import com.pytap.generator.entity.EsCustomerService;
import com.pytap.generator.entity.EsCustomerServiceExample;
import com.pytap.urp.service.CustomerServiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/25 11:05
 */
@Service
public class CustomerServiceServiceImpl implements CustomerServiceService {

    @Resource
    private EsCustomerServiceMapper customerServiceMapper;

    @Override
    public Integer countCustomerService() {
        return customerServiceMapper.countByExample(null);
    }

    @Override
    public Integer insertCustomerService(EsCustomerService customerService) {
        customerService.setCreateTime(new Date());
        return customerServiceMapper.insert(customerService);
    }

    @Override
    public Integer deleteCustomerServiceById(Long id) {
        return customerServiceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateCustomerServiceById(EsCustomerService customerService) {
        customerService.setUpdateTime(new Date());
        return customerServiceMapper.updateByPrimaryKey(customerService);
    }

    @Override
    public EsCustomerService getCustomerService(EsCustomerService queryParam) {

        EsCustomerServiceExample example = new EsCustomerServiceExample();
        EsCustomerServiceExample.Criteria criteria = example.createCriteria();

        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getShopId()) {
            criteria.andShopIdEqualTo(queryParam.getShopId());
        }
        if (null != queryParam.getUserId()) {
            criteria.andUserIdEqualTo(queryParam.getUserId());
        }

        List<EsCustomerService> list = customerServiceMapper.selectByExample(example);

        return list.size() != 0 ? list.get(0) : null;
    }

    @Override
    public List<EsCustomerService> listCustomerServices(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return customerServiceMapper.selectByExample(null);
    }

}
