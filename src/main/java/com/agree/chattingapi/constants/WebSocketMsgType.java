package com.agree.chattingapi.constants;

public enum WebSocketMsgType {
    MSG("01"), ROOM_LIST("02");

    private final String code;

    WebSocketMsgType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
