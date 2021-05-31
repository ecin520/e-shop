package com.pytap.oauth2.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ecin520
 * @date 2020/9/2 17:36
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String avatar;

}
