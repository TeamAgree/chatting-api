package com.agree.chattingapi.constants;

public enum MessageStatus {
    OK("00"), FAIL("01"), DELETE_ME("02"), DELETE_ALL("03"), DELETE_YOU("04");

    private final String code;

    MessageStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
