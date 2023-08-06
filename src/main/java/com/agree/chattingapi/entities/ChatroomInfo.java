package com.agree.chattingapi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "chatroom_info")
@IdClass(ChatroomInfoId.class)
public class ChatroomInfo extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id", nullable = false)
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @Column(name = "room_name", length = 25)
    private String roomName;

    @Column(name = "read_message_seq", nullable = false)
    @ColumnDefault("0")
    private Long readMessageSeq;

    public ChatroomInfo(){}

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}