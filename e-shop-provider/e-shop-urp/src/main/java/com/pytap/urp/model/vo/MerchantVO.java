package com.pytap.urp.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/9/25 11:36
 */
@Data
public class MerchantVO implements Serializable {

    private static final long serialVersionUID = 8174837684710995559L;

    private Long id;

    private Long userId;

    private Long shopId;

    private String username;

    private String receivePayment;

    private Integer status;

    private Date updateTime;

    private Date createTime;

    private UserVO user;
}
