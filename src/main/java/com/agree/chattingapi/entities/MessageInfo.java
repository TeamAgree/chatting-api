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

    @Column(name = "message_seq")
    private Long messageSeq;

    @Column(name = "message_status")
    private MessageStatus messageStatus;

}
