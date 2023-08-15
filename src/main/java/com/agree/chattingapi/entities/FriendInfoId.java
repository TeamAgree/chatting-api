package com.agree.chattingapi.entities;

import java.io.Serializable;

public class FriendInfoId implements Serializable {
    private UserInfo user;
    private UserInfo friend;

    public FriendInfoId(){}

    public FriendInfoId(UserInfo user, UserInfo friend){
        this.user = user;
        this.friend = friend;
    }
}
