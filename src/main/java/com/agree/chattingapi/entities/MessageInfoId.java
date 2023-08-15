package com.agree.chattingapi.entities;

import java.io.Serializable;

public class MessageInfoId implements Serializable {

    private ChatroomInfo chatroom;

    private Long messageSeq;

    public ChatroomInfo getChatroom() {
        return chatroom;
    }

    public void setChatroom(ChatroomInfo chatroom) {
        this.chatroom = chatroom;
    }

    public Long getMessageSeq() {
        return messageSeq;
    }

    public void setMessageSeq(Long messageSeq) {
        this.messageSeq = messageSeq;
    }
}
