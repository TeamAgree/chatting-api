package com.agree.chattingapi.entities;

import com.agree.chattingapi.constants.FriendShipStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "friend_info")
@IdClass(FriendInfoId.class)
public class FriendInfo extends CommonEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @Id
    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false, referencedColumnName = "user_id")
    private UserInfo friend;

    @Column(name = "friendship_status", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private FriendShipStatus friendShipStatus;

    public FriendInfo(){}
    public FriendInfo(UserInfo user, UserInfo friend){
        this.user = user;
        this.friend = friend;
        this.friendShipStatus = FriendShipStatus.FRIEND;
    }

    public String getFriend() {
        return friend.getId();
    }

    public void setFriendShipStatus(FriendShipStatus friendShipStatus) {
        this.friendShipStatus = friendShipStatus;
    }
}