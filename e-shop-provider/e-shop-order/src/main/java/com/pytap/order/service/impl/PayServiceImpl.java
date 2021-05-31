package com.pytap.order.service.impl;

import com.pytap.common.utils.TimeUtil;
import com.pytap.generator.entity.EsOrder;
import com.pytap.order.model.vo.PaySuccessVO;
import com.pytap.order.service.OrderService;
import com.pytap.order.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author Ecin520
 * @date 2020/11/2 17:56
 */
@Service
public class PayServiceImpl implements PayService {

    @Resource
    private OrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PaySuccessVO paySuccessReturn(HttpServletRequest request) {

        String trade_no = request.getParameter("out_trade_no");
        String seller_id = request.getParameter("seller_id");
        String gmt_payment = request.getParameter("gmt_payment");
        String total_amount = request.getParameter("total_amount");
        String trade_status = request.getParameter("trade_status");

        if ("TRADE_SUCCESS".equals(trade_status)) {

            String[] orderNumbers = trade_no.split(",");
            for (String orderNumber : orderNumbers) {

                EsOrder order = new EsOrder();
                order.setOrderNumber(orderNumber);
                order = orderService.getOrder(order);

                if (null == order) {
                    return null;
                }

                order.setTotalPrice(new BigDecimal(total_amount));
                order.setPayType(1);
                order.setPaySerial(seller_id);
                order.setStatus(1);
                order.setPayTime(TimeUtil.parseDate(gmt_payment));
                order.setConfirmStatus(0);

                orderService.updateOrder(order);

                PaySuccessVO vo = new PaySuccessVO();

                BeanUtils.copyProperties(order, vo);

                return vo;
            }


        }
        return null;
    }
}
