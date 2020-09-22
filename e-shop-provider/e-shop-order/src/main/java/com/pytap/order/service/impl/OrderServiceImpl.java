package com.pytap.order.service.impl;

import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.generator.dao.EsOrderMapper;
import com.pytap.generator.entity.EsOrder;
import com.pytap.generator.entity.EsOrderExample;
import com.pytap.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/22 16:45
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private EsOrderMapper orderMapper;

    @Override
    public Integer insertOrder(EsOrder order) {
        order.setCreateTime(new Date());
        return orderMapper.insert(order);
    }

    @Override
    public Integer deleteOrderById(Long id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateOrder(EsOrder order) {
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public EsOrder getOrder(EsOrder queryParam) {
        EsOrderExample example = new EsOrderExample();
        EsOrderExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getOrderNumber()) {
            criteria.andIdEqualTo(queryParam.getOrderNumber());
        }
        List<EsOrder> list = orderMapper.selectByExample(example);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public Pager<EsOrder> listOrders(QueryParam<EsOrder> queryParam) {
        EsOrderExample example = new EsOrderExample();
        EsOrderExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getQueryParam()) {
            EsOrder order = queryParam.getQueryParam();
            // 订单号查询
            if (null != order.getOrderNumber()) {
                criteria.andIdEqualTo(order.getOrderNumber());
            }
            // 订单状态查询
            if (null != order.getStatus()) {
                criteria.andStatusEqualTo(order.getStatus());
            }
            // 订单类型查询，秒杀或者普通订单
            if (null != order.getOrderType()) {
                criteria.andStatusEqualTo(order.getOrderType());
            }
        }
        List<EsOrder> list = orderMapper.selectByExample(example);

        Pager<EsOrder> pager = new Pager<>();
        pager.setPageNum(queryParam.getPageNum());
        pager.setPageSize(queryParam.getPageSize());
        pager.setContent(list);
        pager.setTotal(orderMapper.countByExample(example));

        return pager;
    }
}
