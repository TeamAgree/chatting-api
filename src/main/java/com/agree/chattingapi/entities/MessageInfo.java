package com.agree.chattingapi.entities;

import com.agree.chattingapi.constants.MessageStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "message_info")
@IdClass(MessageInfoId.class)
public class MessageInfo extends CommonEntity{

    @Id
    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatroomInfo chatroom;

    @Id
    @Column(name = "message_seq", nullable = false)
    private Long messageSeq;

    @Column(name = "message_status", nullable = false, length = 3)
    private MessageStatus messageStatus;

    @Column(name = "message",nullable = false, length = 1000)
    private String message;

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

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
