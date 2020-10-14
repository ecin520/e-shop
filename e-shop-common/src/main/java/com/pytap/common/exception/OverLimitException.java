package com.pytap.common.exception;

/**
 * 限制次数异常
 * @author Ecin520
 * @date 2020/8/19 14:16
 */
public class OverLimitException extends Exception {

    private static final long serialVersionUID = -12713000583387394L;

    public OverLimitException(String msg) {
        super(msg);
    }
}
