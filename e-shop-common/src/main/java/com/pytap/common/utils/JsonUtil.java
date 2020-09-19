package com.pytap.common.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Ecin520
 * @date 2020/8/31 21:30
 */
public class JsonUtil {
    public static String ExceptionObject(Integer code, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("message", message);
        return jsonObject.toJSONString();
    }
}
