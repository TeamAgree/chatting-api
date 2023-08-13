package com.agree.chattingapi.constants;

public enum StatusCode {
    SUCCESS(100), FAIL(101), RE_GENERATE_TOKEN(102);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
