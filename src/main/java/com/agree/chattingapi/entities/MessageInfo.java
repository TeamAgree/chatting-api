package com.agree.chattingapi.entities;

import com.agree.chattingapi.constants.MessageStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "message_info")
public class MessageInfo extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatroomInfo chatroom;

    @Column(name = "message_seq", nullable = false)
    private Long messageSeq;

    @Column(name = "message_status", nullable = false, length = 2)
    private MessageStatus messageStatus;

    @Column(name = "message",nullable = false, length = 1000)
    private String message;

}
