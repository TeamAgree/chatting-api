package com.agree.chattingapi.responses;

import com.agree.chattingapi.constants.StatusCode;
import org.antlr.v4.runtime.misc.NotNull;

public class CommonResponse<T> {
    @NotNull
    private StatusCode code;

    private String message;

    private T result;

    public CommonResponse(){}

    public CommonResponse(@NotNull StatusCode code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public CommonResponse(T result) {
        this(StatusCode.SUCCESS, "SUCCESS", result);
    }

    public CommonResponse(String message, T result) {
        this(StatusCode.FAIL, message, result);
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
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
