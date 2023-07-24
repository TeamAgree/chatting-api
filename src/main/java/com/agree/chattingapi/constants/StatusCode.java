package com.agree.chattingapi.constants;

public enum StatusCode {
    SUCCESS("00"), FAIL("01"), RE_GENERATE_TOKEN("02");

    private final String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
