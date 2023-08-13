package com.agree.chattingapi.dtos;

import com.agree.chattingapi.constants.StatusCode;

public class CommonResponse<T> {

    private int code;

    private String message;

    private T result;

    public CommonResponse(){}

    public CommonResponse(StatusCode code, String message, T result) {
        this.code = code.getCode();
        this.message = message;
        this.result = result;
    }

    public CommonResponse(T result) {
        this(StatusCode.SUCCESS, "SUCCESS", result);
    }

    public CommonResponse(String message, T result) {
        this(StatusCode.FAIL, message, result);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
