package com.pytap.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.EsOrderReturnReasonMapper;
import com.pytap.generator.entity.EsOrderReturnReason;
import com.pytap.generator.entity.EsOrderReturnReasonExample;
import com.pytap.order.service.OrderReturnReasonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/23 17:31
 */
@Service
public class OrderReturnReasonServiceImpl implements OrderReturnReasonService {

    @Resource
    private EsOrderReturnReasonMapper orderReturnReasonMapper;

    @Override
    public Integer insertOrderReturnReason(EsOrderReturnReason orderReturnReason) {
        orderReturnReason.setCreateTime(new Date());
        return orderReturnReasonMapper.insert(orderReturnReason);
    }

    @Override
    public Integer deleteOrderReturnReasonById(Long id) {
        return orderReturnReasonMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateOrderReturnReason(EsOrderReturnReason orderReturnReason) {
        orderReturnReason.setUpdateTime(new Date());
        return orderReturnReasonMapper.updateByPrimaryKeySelective(orderReturnReason);
    }

    @Override
    public EsOrderReturnReason getOrderReturnReason(EsOrderReturnReason queryParam) {
        EsOrderReturnReasonExample example = new EsOrderReturnReasonExample();
        EsOrderReturnReasonExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getCause()) {
            criteria.andCauseEqualTo(queryParam.getCause());
        }
        List<EsOrderReturnReason> list = orderReturnReasonMapper.selectByExample(example);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public Pager<EsOrderReturnReason> listOrderReturnReasons(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<EsOrderReturnReason> list = orderReturnReasonMapper.selectByExample(null);
        Pager<EsOrderReturnReason> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(orderReturnReasonMapper.countByExample(null));
        return pager;
    }
}
