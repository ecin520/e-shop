package com.pytap.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pytap.api.model.vo.MemberVO;
import com.pytap.api.service.api.product.ProductFeignService;
import com.pytap.api.service.api.product.ShopFeignService;
import com.pytap.api.service.api.sale.CouponFeignService;
import com.pytap.api.service.api.urp.MemberFeignService;
import com.pytap.common.constant.HttpCode;
import com.pytap.common.constant.OrderStatus;
import com.pytap.common.constant.TimeConstant;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.common.utils.UUIDUtil;
import com.pytap.generator.dao.EsDeliveryMapper;
import com.pytap.generator.dao.EsOrderMapper;
import com.pytap.generator.dao.EsOrderProductMapper;
import com.pytap.generator.dao.EsReceiverAddressMapper;
import com.pytap.generator.entity.*;
import com.pytap.order.model.dto.OrderParamDTO;
import com.pytap.order.model.dto.OrderQueryDTO;
import com.pytap.order.model.vo.OrderProductVO;
import com.pytap.order.model.vo.OrderVO;
import com.pytap.order.mq.OrderSender;
import com.pytap.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/22 16:45
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private EsOrderMapper orderMapper;

    @Resource
    private EsReceiverAddressMapper receiverAddressMapper;

    @Resource
    private EsOrderProductMapper orderProductMapper;

    @Resource
    private EsDeliveryMapper deliveryMapper;

    @Resource
    private MemberFeignService memberFeignService;

    @Resource
    private ProductFeignService productFeignService;

    @Resource
    private CouponFeignService couponFeignService;

    @Resource
    private ShopFeignService shopFeignService;

    @Resource
    private OrderSender orderSender;

    @Override
    public Integer insertOrder(EsOrder order) {
        order.setCreateTime(new Date());
        return orderMapper.insert(order);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertOrderByParam(OrderParamDTO orderParamDTO) throws GeneralException {
        EsOrder order = new EsOrder();
        BeanUtils.copyProperties(orderParamDTO, order);

        // 生成订单号
        order.setOrderNumber(UUIDUtil.uuidNumberString());
        // 修改状态为待支付
        order.setStatus(OrderStatus.WAITING_FOR_PAYMENT);
        order.setCreateTime(new Date());
        orderMapper.insert(order);

        // 添加订单到延时队列，区分普通订单和秒杀订单
        if (0 == orderParamDTO.getOrderType()) {
            // 添加到订单商品
            List<EsOrderProduct> orderProducts = orderParamDTO.getOrderProducts();
            for (EsOrderProduct orderProduct : orderProducts) {
                orderProduct.setOrderId(order.getId());
                orderProductMapper.insert(orderProduct);

                // 远程调用减库存系统
                ResultEntity<Object> resultEntity = productFeignService.reduceSkuProductStock(orderProduct.getSkuId());

                // 如果减库存失败，则进行回滚
                if (200 != resultEntity.getCode()) {
                    throw new GeneralException("库存不足或库存系统异常，订单创建失败");
                }
            }
            orderSender.send(JSONObject.toJSONString(orderParamDTO), TimeConstant.NORMAL_SALE_OVER_TIME);
        } else if (1 == orderParamDTO.getOrderType()) {
            // 添加到订单商品，先生成订单，等到支付的时候再判断库存
            List<EsOrderProduct> orderProducts = orderParamDTO.getOrderProducts();
            for (EsOrderProduct orderProduct : orderProducts) {
                orderProduct.setOrderId(order.getId());
                orderProductMapper.insert(orderProduct);
            }
            orderSender.send(JSONObject.toJSONString(orderParamDTO), TimeConstant.FLASH_SALE_OVER_TIME);
        }

        return 1;
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
    public Pager<EsOrder> listOrders(QueryParam<OrderQueryDTO> queryParam) {
        EsOrderExample example = new EsOrderExample();
        EsOrderExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getQueryParam()) {
            OrderQueryDTO orderQueryDTO = queryParam.getQueryParam();
            // 订单号查询
            if (null != orderQueryDTO.getOrderNumber()) {
                criteria.andOrderNumberEqualTo(orderQueryDTO.getOrderNumber());
            }
            // 订单状态查询
            if (null != orderQueryDTO.getStatus()) {
                criteria.andStatusEqualTo(orderQueryDTO.getStatus());
            }
            // 订单类型查询，秒杀或者普通订单
            if (null != orderQueryDTO.getOrderType()) {
                criteria.andStatusEqualTo(orderQueryDTO.getOrderType());
            }
            // 区间时间内查询订单
            if (null != orderQueryDTO.getStartTime() && null != orderQueryDTO.getEndTime()) {
                criteria.andCreateTimeBetween(orderQueryDTO.getStartTime(), orderQueryDTO.getEndTime());
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

    @Override
    public OrderVO getOrderVO(EsOrder queryParam) {
        OrderVO vo = new OrderVO();

        EsOrderExample example = new EsOrderExample();
        EsOrderExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getOrderNumber()) {
            criteria.andOrderNumberEqualTo(queryParam.getOrderNumber());
        }
        if (null == queryParam.getId() && null == queryParam.getOrderNumber()) {
            return null;
        }

        List<EsOrder> list  = orderMapper.selectByExample(example);

        if (!list.isEmpty()) {

            EsOrder order = list.get(0);
            BeanUtils.copyProperties(order, vo);

            // 添加会员信息
            if (null != order.getMemberId()) {
                // 调用远程接口通过会员id获取会员
                EsMember member = new EsMember();
                member.setId(order.getMemberId());
                ResultEntity<MemberVO> resultEntity = memberFeignService.getMemberVO(member);
                if (HttpCode.SUCCESS.equals(resultEntity.getCode())) {
                    vo.setMember(resultEntity.getData());
                } else {
                    logger.error(resultEntity.getMessage());
                    vo.setMember(null);
                }
            }

            //添加店铺信息
            if (null != order.getShopId()) {
                EsShop shop = new EsShop();
                shop.setId(order.getShopId());
                // 远程调用店铺查询接口
                ResultEntity<EsShop> resultEntity = shopFeignService.getShop(shop);
                if (HttpCode.SUCCESS.equals(resultEntity.getCode())) {
                    vo.setShop(resultEntity.getData());
                } else {
                    logger.error(resultEntity.getMessage());
                    vo.setShop(null);
                }
            }

            // 添加优惠券信息
            if (null != order.getCouponId()) {
                EsCoupon coupon = new EsCoupon();
                coupon.setId(order.getCouponId());
                // 远程调用优惠券查询接口
                ResultEntity<EsCoupon> resultEntity1 = couponFeignService.getCoupon(coupon);
                if (HttpCode.SUCCESS.equals(resultEntity1.getCode())) {
                    vo.setCoupon(resultEntity1.getData());
                } else {
                    logger.error(resultEntity1.getMessage());
                    vo.setCoupon(null);
                }
            }

            // 添加物流信息
            if (null != order.getDeliveryId()) {
                vo.setDelivery(deliveryMapper.selectByPrimaryKey(order.getDeliveryId()));
            }

            // 收货地址
            if (null != order.getReceiverAddressId()) {
                vo.setReceiverAddress(receiverAddressMapper.selectByPrimaryKey(order.getReceiverAddressId()));
            }

            EsOrderProductExample example1 = new EsOrderProductExample();
            EsOrderProductExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andOrderIdEqualTo(order.getId());
            List<EsOrderProduct> orderProductList = orderProductMapper.selectByExample(example1);

            for (EsOrderProduct orderProduct : orderProductList) {
                OrderProductVO orderProductVO = new OrderProductVO();
                BeanUtils.copyProperties(orderProduct, orderProductVO);

                if (null != orderProduct.getSkuId()) {
                    EsSkuProduct skuProduct = new EsSkuProduct();
                    skuProduct.setId(orderProduct.getSkuId());
                    // 远程调用skuId查询sku
                    ResultEntity<EsSkuProduct> resultEntity1 = productFeignService.getSkuProduct(skuProduct);
                    if (HttpCode.SUCCESS.equals(resultEntity1.getCode())) {
                        orderProductVO.setSkuProduct(resultEntity1.getData());
                    } else {
                        logger.error(resultEntity1.getMessage());
                        orderProductVO.setSkuProduct(null);
                    }
                }

                if (null == vo.getProducts()) {
                    vo.setProducts(new ArrayList<>(16));
                }
                vo.getProducts().add(orderProductVO);
            }
            return vo;
        }

        return null;
    }

    @Override
    public EsOrder getOrder(EsOrder queryParam) {
        if (null != queryParam.getId()) {
            return orderMapper.selectByPrimaryKey(queryParam.getId());
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer cancelOrder(OrderParamDTO orderParamDTO) throws GeneralException {

        // 更新订单状态为订单关闭，不可再次发起
        EsOrder order = new EsOrder();
        BeanUtils.copyProperties(orderParamDTO, order);
        order.setStatus(OrderStatus.CLOSED);
        orderMapper.updateByPrimaryKeySelective(order);

        // 如果是秒杀订单，则不减库存，因为秒杀订单的库存是在付款后才减的
        if (1 == orderParamDTO.getOrderType()) {
            return 1;
        }

        List<EsOrderProduct> orderProducts = orderParamDTO.getOrderProducts();
        for (EsOrderProduct orderProduct : orderProducts) {
            // 远程调用增加库存服务
            ResultEntity<Object> resultEntity = productFeignService.increaseSkuProductStock(orderProduct.getSkuId());
            if (200 != resultEntity.getCode()) {
                logger.error("恢复库存失败，sku id为{}，开始事务回滚，且重新添加到延时队列。", orderProduct.getSkuId());
                orderSender.send(JSONObject.toJSONString(orderParamDTO), 300L);
                throw new GeneralException(resultEntity.getMessage());
            } else {
                logger.info("库存恢复成功，sku id 为 {}", orderProduct.getSkuId());
            }
        }

        return 1;

    }
}
