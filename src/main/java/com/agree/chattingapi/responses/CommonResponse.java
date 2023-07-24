package com.agree.chattingapi.responses;

import org.antlr.v4.runtime.misc.NotNull;

public class CommonResponse<T> {
    @NotNull
    private String code;

    private String message;

    private T result;

    public CommonResponse(){}

    public CommonResponse(@NotNull String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public CommonResponse(T result) {
        this("00", "SUCCESS", result);
    }

    public CommonResponse(String message, T result) {
        this("01", message, result);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
