package com.agree.chattingapi.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_info")
public class UserInfo extends CommonEntity {

    @Id
    @Column(name = "user_id", nullable = false, length = 15)
    private String id;

    @Column(name = "password", nullable = false)
    private String pw;

    @Column(name = "name", nullable = false, length = 12)
    private String name;

    @Column(name = "nick_name", nullable = false, length = 12)
    private String nickName;

    @Column(name = "birth", nullable = false, length = 6)
    private String birth;

    @Column(name = "status", nullable = false, length = 1)
    private String status = "Y";

    @Column(name = "roles", nullable = false)
    private String roles = "USER";

    @Column(name = "push_key")
    private String pushKey;

    @OneToMany(mappedBy = "user")
    private List<FriendInfo> friends;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private TokenInfo token;

    @OneToMany(mappedBy = "user")
    private List<UserChatroom> chatroom;

    public List<String> getFriends() {
        if (this.friends != null) {
            return friends.stream()
                    .map(FriendInfo::getFriendId)
                    .collect(Collectors.toList());
        }else {
            return null;
        }
    }

    public UserInfo(String id, String pw, String name, String nickName, String birth) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickName = nickName;
        this.birth = birth;
    }

    public UserInfo(String id, String pw, String name, String nickName, String birth, String roles) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickName = nickName;
        this.birth = birth;
        this.roles = roles;
    }

    public UserInfo() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName(){
        return nickName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getRoles() {
        return roles;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPushKey() {
        return pushKey;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }
}
