package com.pytap.sale.exception;

import com.pytap.common.constant.HttpCode;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.exception.OverLimitException;
import com.pytap.common.utils.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 异常处理类
 * @author Ecin520
 * @date 2020/8/19 14:17
 */
@RestControllerAdvice
public class ExceptionInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

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
        return ResultEntity.fail(400, e.getMessage());
    }

    /**
     * 数据库操作异常
     * */
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResultEntity<Object> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        logger.error(e.getMessage());
        return ResultEntity.fail(HttpCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * HttpMessageNotReadableException
     * */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResultEntity<Object> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error(e.getMessage());
        return ResultEntity.fail(HttpCode.BAD_REQUEST, e.getMessage());
    }

}
