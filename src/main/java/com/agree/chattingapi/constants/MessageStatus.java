package com.agree.chattingapi.constants;

public enum MessageStatus {
    SUCCESS(100), FAIL(101), DELETE_ME(102), DELETE_ALL(103), DELETE_YOU(104);

    private final int code;

    MessageStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
