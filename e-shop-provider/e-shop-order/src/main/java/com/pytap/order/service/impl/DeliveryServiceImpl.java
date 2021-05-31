package com.pytap.order.service.impl;

import com.pytap.common.constant.DeliveryStatus;
import com.pytap.common.constant.OrderStatus;
import com.pytap.common.utils.ObjectUtil;
import com.pytap.common.utils.UUIDUtil;
import com.pytap.generator.dao.EsDeliveryMapper;
import com.pytap.generator.entity.EsDelivery;
import com.pytap.generator.entity.EsDeliveryExample;
import com.pytap.generator.entity.EsOrder;
import com.pytap.order.model.dto.DeliveryDTO;
import com.pytap.order.service.DeliveryService;
import com.pytap.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/11/4 14:53
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Resource
    private EsDeliveryMapper deliveryMapper;

    @Resource
    private OrderService orderService;

    @Override
    public Integer insertDelivery(EsDelivery delivery) {
        delivery.setCreateTime(new Date());
        return deliveryMapper.insert(delivery);
    }

    @Override
    public Integer deleteDeliveryById(Long id) {
        return deliveryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateDelivery(EsDelivery delivery) {
        return deliveryMapper.updateByPrimaryKeySelective(delivery);
    }

    @Override
    public EsDelivery getDeliveryByParam(EsDelivery delivery) {
        EsDeliveryExample example = new EsDeliveryExample();
        EsDeliveryExample.Criteria criteria = example.createCriteria();

        if (null != delivery.getId()) {
            criteria.andIdEqualTo(delivery.getId());
        }

        if (null != delivery.getDeliveryNumber()) {
            criteria.andDeliveryNumberEqualTo(delivery.getDeliveryNumber());
        }

        List<EsDelivery> deliveries = deliveryMapper.selectByExample(example);

        return !deliveries.isEmpty() ? deliveries.get(0) : null;
    }

    @Override
    public List<EsDelivery> listDeliveries(EsDelivery queryParam) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertOrderDelivery(DeliveryDTO deliveryDTO) {
        EsOrder order = new EsOrder();

        // 状态修改为已发货
        order.setStatus(OrderStatus.ALREADY_FOR_DELIVERY);

        if (null != deliveryDTO.getOrderNumber()) {
            order.setOrderNumber(deliveryDTO.getOrderNumber());
        }

        if (null != deliveryDTO.getOrderId()) {
            order.setId(deliveryDTO.getOrderId());
        }

        if (ObjectUtil.isAllNull(deliveryDTO.getOrderNumber(), deliveryDTO.getOrderId())) {
            return 0;
        }

        EsDelivery delivery = deliveryDTO.getDelivery();

        // 插入物流信息
        if (null != delivery) {
            delivery.setDeliveryNumber(UUIDUtil.uuidNumberString());
            insertDelivery(delivery);
            order.setDeliveryId(delivery.getId());
            orderService.updateOrder(order);
            return 1;
        }

        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer confirmReceipt(String deliveryNumber, Long orderId) {

        // 更新订单信息
        EsOrder order = new EsOrder();
        order.setId(orderId);
        // 状态为已完成
        order.setStatus(OrderStatus.FINISHED);
        // 更新收货时间
        order.setReceiveTime(new Date());
        orderService.updateOrder(order);

        // 更新物流信息
        EsDelivery delivery = new EsDelivery();
        delivery.setDeliveryNumber(deliveryNumber);
        delivery.setDeliveryStatus(DeliveryStatus.ARRIVED);

        EsDeliveryExample example = new EsDeliveryExample();
        EsDeliveryExample.Criteria criteria = example.createCriteria();
        criteria.andDeliveryNumberEqualTo(deliveryNumber);

        deliveryMapper.updateByExampleSelective(delivery, example);

        return 1;
    }
}
