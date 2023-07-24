package com.agree.chattingapi.entities;

import com.agree.chattingapi.constants.FriendShipStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "friend_info")
@IdClass(FriendInfoId.class)
public class FriendInfo extends CommonEntity {

    @Id
    @Column(name = "user_id", nullable = false, length = 15)
    private String id;

    @Id
    @Column(name = "friend_id", nullable = false, length = 15)
    private String friendId;

    @Column(name = "friendship_status", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private FriendShipStatus friendShipStatus;

    public FriendInfo(){}
    public FriendInfo(String id, String friendId){
        this.id = id;
        this.friendId = friendId;
        this.friendShipStatus = FriendShipStatus.FRIEND;
    }

    public void setFriendShipStatus(FriendShipStatus friendShipStatus) {
        this.friendShipStatus = friendShipStatus;
    }
}