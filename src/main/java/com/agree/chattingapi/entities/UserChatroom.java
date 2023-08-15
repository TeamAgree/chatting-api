package com.agree.chattingapi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "user_chatroom")
@IdClass(UserChatroomId.class)
public class UserChatroom extends CommonEntity{

    @ManyToOne
    @Id
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @ManyToOne
    @Id
    @JoinColumn(name = "chatroom_id")
    private ChatroomInfo chatroom;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "read_message_seq", nullable = false)
    @ColumnDefault("0")
    private Long readMessageSeq = 0L;

    public UserChatroom() {}

    public UserChatroom(UserInfo user, String name, ChatroomInfo chatroom){
        this.user = user;
        this.name = name;
        this.chatroom = chatroom;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public ChatroomInfo getChatroom() {
        return chatroom;
    }

    public void setChatroom(ChatroomInfo chatroom) {
        this.chatroom = chatroom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getReadMessageSeq() {
        return readMessageSeq;
    }

    public void setReadMessageSeq(Long readMessageSeq) {
        this.readMessageSeq = readMessageSeq;
    }
}
