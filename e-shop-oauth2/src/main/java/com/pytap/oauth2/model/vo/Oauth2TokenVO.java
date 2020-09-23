package com.pytap.oauth2.model.vo;

import lombok.Data;

/**
 * @author Ecin520
 * @date 2020/8/31 18:11
 */
@Data
public class Oauth2TokenVO {

    private String accessToken;

    private String refreshToken;

    private String tokenHead;

    private int expiresIn;

}
