package com.pytap.common.constant;

/**
 * @author Ecin520
 * @date 2020/8/21 11:51
 */
public class HttpCode {

    /**
     * 成功返回码
     * */
    public static final Integer SUCCESS = 200;

    public static final Integer BAD_REQUEST = 400;
    public static final Integer UNAUTHORIZED = 401;
    public static final Integer FORBIDDEN = 403;
    public static final Integer NOT_FOUND = 404;
    public static final Integer NOT_ACCEPTABLE = 406;

    /**
     * 服务器错误
     * */
    public static final Integer INTERNAL_SERVER_ERROR = 500;

    /**
     * 鉴权失败
     * */
    public static final Integer OAUTH2_AUTH_ERROR = 4000;

    /**
     * 无权限
     * */
    public static final Integer OAUTH2_NO_AUTH = 4001;

    /**
     * token失效或者没有token
     * */
    public static final Integer OAUTH2_TOKEN_ERROR = 4003;



}
