package com.pytap.api.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/8/19 15:13
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 396586324227803748L;

    private Long id;

    private String username;

    private String telephone;

    private String avatar;

    private String email;

    private String nickname;

    private String note;

    private Integer status;

    private Date updateTime;

    private Date loginTime;

    private Date createTime;

}
