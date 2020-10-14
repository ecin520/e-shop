package com.pytap.common.exception;

/**
 * 自定义异常
 * @author Ecin520
 * @date 2020/8/19 14:52
 */
public class GeneralException extends Exception {

    private static final long serialVersionUID = -2282304269742635805L;

    public GeneralException(String msg) {
        super(msg);
    }
}