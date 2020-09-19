package com.pytap.oauth2.exception;

import com.alibaba.fastjson.JSONObject;
import com.pytap.common.constant.HttpCode;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.exception.OverLimitException;
import com.pytap.common.utils.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 异常处理类
 * @author Ecin520
 * @date 2020/8/19 14:17
 */
@RestControllerAdvice
public class ExceptionHandling {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

    /**
     * 操作超时异常
     * */
    @ExceptionHandler(value = OverLimitException.class)
    public ResultEntity<Object> overLimitException(OverLimitException e) {
        logger.error(e.getMessage());
        return ResultEntity.fail(403, e.getMessage());
    }


    @ExceptionHandler(value = GeneralException.class)
    public ResultEntity<Object> generalException(GeneralException e) {
        logger.error(e.getMessage());
        JSONObject jsonObject = JSONObject.parseObject(e.getMessage());
        return ResultEntity.fail((Integer) jsonObject.get("code"), (String) jsonObject.get("message"));
    }

    /**
     * 数据库操作异常
     * */
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResultEntity<Object> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        logger.error(e.getMessage());
        return ResultEntity.fail(HttpCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = OAuth2Exception.class)
    public ResultEntity<Object> oAuth2Exception(OAuth2Exception e) {
        logger.error(e.getMessage());
        return ResultEntity.fail(HttpCode.UNAUTHORIZED, e.getMessage());
    }

}
