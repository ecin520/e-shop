package com.pytap.order.service.impl;

import com.pytap.common.utils.ObjectUtil;
import com.pytap.generator.dao.EsReceiverAddressMapper;
import com.pytap.generator.entity.EsReceiverAddress;
import com.pytap.generator.entity.EsReceiverAddressExample;
import com.pytap.order.service.ReceiverAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/22 16:37
 */
@Service
public class ReceiverAddressServiceImpl implements ReceiverAddressService {

    @Resource
    private EsReceiverAddressMapper receiverAddressMapper;

    @Override
    public Integer insertReceiverAddress(EsReceiverAddress receiverAddress) {
        receiverAddress.setCreateTime(new Date());
        return receiverAddressMapper.insert(receiverAddress);
    }

    @Override
    public Integer deleteReceiverAddressById(Long id, Long userId) {
        EsReceiverAddressExample example = new EsReceiverAddressExample();
        EsReceiverAddressExample.Criteria criteria = example.createCriteria();

        if (null == id || null == userId) {
            return null;
        }

        criteria.andUserIdEqualTo(userId).andIdEqualTo(id);
        return receiverAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateReceiverAddress(EsReceiverAddress receiverAddress) {
        EsReceiverAddressExample example = new EsReceiverAddressExample();
        EsReceiverAddressExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(receiverAddress.getUserId()).andIdEqualTo(receiverAddress.getId());
        return receiverAddressMapper.updateByExampleSelective(receiverAddress, example);
    }

    @Override
    public EsReceiverAddress getReceiverAddressById(Long id) {
        return receiverAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EsReceiverAddress> listReceiverAddresses(EsReceiverAddress queryParam) {
        EsReceiverAddressExample example = new EsReceiverAddressExample();
        EsReceiverAddressExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getUserId()) {
            criteria.andUserIdEqualTo(queryParam.getUserId());
            return receiverAddressMapper.selectByExample(example);
        }
        return null;
    }

}
