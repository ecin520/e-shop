package com.pytap.common.utils;

import com.pytap.common.constant.HttpCode;

/**
 * 返回对象
 * @author Ecin520
 * @date 2020/8/4 17:04
 */
public class ResultEntity<T> {

    private Integer code;

    private String message;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResultEntity<T> success() {
        ResultEntity<T> resultEntity = new ResultEntity<>();
        resultEntity.setCode(HttpCode.SUCCESS);
        resultEntity.setMessage("success");
        return resultEntity;
    }

    public static <T> ResultEntity<T> success(String message) {
        ResultEntity<T> resultEntity = new ResultEntity<>();
        resultEntity.setCode(HttpCode.SUCCESS);
        resultEntity.setMessage(message);
        return resultEntity;
    }

    public static <T> ResultEntity<T> success(T data) {
        ResultEntity<T> resultEntity = new ResultEntity<>();
        resultEntity.setMessage("success");
        resultEntity.setCode(HttpCode.SUCCESS);
        resultEntity.setData(data);
        return resultEntity;
    }


    public static <T> ResultEntity<T> success(String message, T data) {
        ResultEntity<T> resultEntity = new ResultEntity<>();
        resultEntity.setCode(HttpCode.SUCCESS);
        resultEntity.setMessage(message);
        resultEntity.setData(data);
        return resultEntity;
    }

    public static <T> ResultEntity<T> fail() {
        ResultEntity<T> resultEntity = new ResultEntity<>();
        resultEntity.setCode(HttpCode.INTERNAL_SERVER_ERROR);
        resultEntity.setMessage("fail");
        return resultEntity;
    }

    public static <T> ResultEntity<T> fail(String message) {
        ResultEntity<T> resultEntity = new ResultEntity<>();
        resultEntity.setCode(HttpCode.INTERNAL_SERVER_ERROR);
        resultEntity.setMessage(message);
        return resultEntity;
    }

    public static <T> ResultEntity<T> fail(T data) {
        ResultEntity<T> resultEntity = new ResultEntity<>();
        resultEntity.setData(data);
        return resultEntity;
    }

    public static <T> ResultEntity<T> fail(Integer code, String message) {
        ResultEntity<T> resultEntity = new ResultEntity<>();
        resultEntity.setCode(code);
        resultEntity.setMessage(message);
        return resultEntity;
    }

    public static <T> ResultEntity<T> fail(Integer code, String message, T data) {
        ResultEntity<T> resultEntity = new ResultEntity<>();
        resultEntity.setCode(code);
        resultEntity.setMessage(message);
        resultEntity.setData(data);
        return resultEntity;
    }

}
