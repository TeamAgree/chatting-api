package com.agree.chattingapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "chatroom_info")
public class ChatroomInfo extends CommonEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    @Column(name = "room_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_chatroom_id")
    private UserChatroom userChatroom;


}
