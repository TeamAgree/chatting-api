package com.agree.chattingapi.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_chatroom")
public class UserChatroom extends CommonEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_chatroom_id")
    private Long id;

    @OneToMany(mappedBy = "userChatroom")
    private List<UserInfo> users;

    @OneToMany(mappedBy = "userChatroom")
    private List<ChatroomInfo> chatrooms;

}
