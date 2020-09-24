package com.auto.common;

import java.io.Serializable;
import java.util.List;

/**
 * 通用web返回类
 */
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = -8362438063647375722L;
    public static final Integer CODE_OK = 200;
    public static final String MESSAGE_OK = "OK";

    public static final Integer CODE_FAILED = 400;
    public static final String MESSAGE_FAILED = "FAILED";

    public static final Integer CODE_EXCEPTION = 500;
    public static final String MESSAGE_EXCEPTION = "EXCEPTION";

    private T data;

    /**
     * 响应的状态码
     */
    private Integer code = CODE_OK;

    /**
     * 响应的信息
     */
    private String message = MESSAGE_OK;



    /**
     * 无参构造函数默认初始化成功状态
     */
    public CommonResponse() {
        code= CODE_OK;
        message = MESSAGE_OK;
    }

    public CommonResponse(Integer code, String message) {
        this.setMessage(message);
        this.setCode(code);
    }

    /** 响应状态码 */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /** 响应消息 */
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
}
