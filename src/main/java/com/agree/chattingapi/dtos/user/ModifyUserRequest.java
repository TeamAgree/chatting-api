package com.agree.chattingapi.dtos.user;

public class ModifyUserRequest {

    private String id;
    private String name;
    private String birth;
    private String pw;
    private String pushKey;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public String getPw(){
        return pw;
    }

    public String getPushKey(){
        return pushKey;
    }
}
