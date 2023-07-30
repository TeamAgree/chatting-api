package com.agree.chattingapi.entities;

import java.io.Serializable;

public class FriendInfoId implements Serializable {
    private UserInfo user;
    private String friendId;

    public FriendInfoId(){}

    public FriendInfoId(UserInfo user, String friendId){
        this.user = user;
        this.friendId = friendId;
    }
}
