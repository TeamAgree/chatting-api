package com.agree.chattingapi.entities;

import java.io.Serializable;

public class FriendInfoId implements Serializable {
    private String id;
    private String friendId;

    public FriendInfoId(){}

    public FriendInfoId(String id, String friendId){
        this.id = id;
        this.friendId = friendId;
    }
}
