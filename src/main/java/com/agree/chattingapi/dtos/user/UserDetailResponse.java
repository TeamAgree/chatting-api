package com.agree.chattingapi.dtos.user;

import com.agree.chattingapi.entities.UserInfo;

public class UserDetailResponse {

    private String id;
    private String name;
    private String nickName;
    private String birth;
    private String mobileNo;
    private String roles;
    private String pushKey;

    public UserDetailResponse(){}

    public UserDetailResponse(UserInfo userInfo){
        this.id = userInfo.getId();
        this.name = userInfo.getName();
        this.nickName = userInfo.getNickName();
        this.birth = userInfo.getBirth();
        this.mobileNo = userInfo.getMobileNo();
        this.roles = userInfo.getRoles();
        this.pushKey = userInfo.getPushKey();
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

    public String getNickName() {
        return nickName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getRoles() {
        return roles;
    }

    public String getPushKey() {
        return pushKey;
    }
}
