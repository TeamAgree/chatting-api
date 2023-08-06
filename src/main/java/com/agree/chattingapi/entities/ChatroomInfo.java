package com.agree.chattingapi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "chatroom_info")
public class ChatroomInfo extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @Column(name = "room_name", length = 25)
    private String roomName;

    @Column(name = "read_message_seq", nullable = false)
    @ColumnDefault("0")
    private Long readMessageSeq;

    @OneToMany(mappedBy = "chatroom")
    private List<MessageInfo> messageList;

    public ChatroomInfo(){}

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
