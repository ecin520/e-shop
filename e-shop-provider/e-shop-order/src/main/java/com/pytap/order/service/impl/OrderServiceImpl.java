package com.pytap.order.service.impl;

import com.pytap.api.model.vo.MemberVO;
import com.pytap.api.service.api.product.ProductFeignService;
import com.pytap.api.service.api.product.ShopFeignService;
import com.pytap.api.service.api.sale.CouponFeignService;
import com.pytap.api.service.api.urp.MemberFeignService;
import com.pytap.common.constant.HttpCode;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.dao.EsDeliveryMapper;
import com.pytap.generator.dao.EsOrderMapper;
import com.pytap.generator.dao.EsOrderProductMapper;
import com.pytap.generator.dao.EsReceiverAddressMapper;
import com.pytap.generator.entity.*;
import com.pytap.order.model.dto.OrderQueryDTO;
import com.pytap.order.model.vo.OrderProductVO;
import com.pytap.order.model.vo.OrderVO;
import com.pytap.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

                if (null != orderProduct.getCouponId()) {
                    EsCoupon coupon = new EsCoupon();
                    coupon.setId(orderProduct.getCouponId());
                    // 远程调用优惠券查询接口
                    ResultEntity<EsCoupon> resultEntity1 = couponFeignService.getCoupon(coupon);
                    if (HttpCode.SUCCESS.equals(resultEntity1.getCode())) {
                        orderProductVO.setCoupon(resultEntity1.getData());
                    } else {
                        logger.error(resultEntity1.getMessage());
                        orderProductVO.setCoupon(null);
                    }
                }

                if (null != orderProduct.getDeliveryId()) {
                    orderProductVO.setDelivery(deliveryMapper.selectByPrimaryKey(orderProduct.getDeliveryId()));
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
}
