package com.pytap.common.exception;

/**
 * 限制次数异常
 * @author Ecin520
 * @date 2020/8/19 14:16
 */
public class OverLimitException extends Exception {
    public OverLimitException(String msg) {
        super(msg);
    }
}
