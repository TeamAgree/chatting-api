package com.agree.chattingapi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chatroom_info")
public class ChatroomInfo extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "chatroom")
    private List<UserChatroom> users;

    @OneToMany(mappedBy = "chatroom")
    private List<MessageInfo> messageList;


}
