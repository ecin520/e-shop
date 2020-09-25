package com.pytap.api.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/9/25 11:35
 */
@Data
public class MemberVO implements Serializable {

    private static final long serialVersionUID = -3949682809330431537L;

    private Long id;

    private Long userId;

    private String memberNumber;

    private String username;

    private Date createTime;

    private Date updateTime;

    private UserVO user;

}
