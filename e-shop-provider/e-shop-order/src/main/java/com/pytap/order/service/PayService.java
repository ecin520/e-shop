package com.pytap.order.service;

import com.pytap.order.model.vo.PaySuccessVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ecin520
 * @date 2020/11/2 17:55
 */
public interface PayService {

    /**
     * @param request servlet
     * @return PaySuccessVO
     * */
    PaySuccessVO paySuccessReturn(HttpServletRequest request);

}
