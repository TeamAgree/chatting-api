package com.agree.chattingapi.dtos.user;

import com.agree.chattingapi.entities.UserInfo;

public class UserDetailResponse {

    private String id;
    private String name;
    private String birth;

    public UserDetailResponse(){}

    public UserDetailResponse(UserInfo userInfo){
        this.id = userInfo.getId();
        this.name = userInfo.getName();
        this.birth = userInfo.getBirth();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }
}
