package com.agree.chattingapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "token_info")
public class TokenInfo extends CommonEntity{

    @Id
    @Column(name = "user_id")
    private String userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @Column(name = "token", columnDefinition = "TEXT")
    private String token;

    @Column(name = "roles", nullable = false, length = 5)
    private String roles;

    public TokenInfo() {}
    public TokenInfo(UserInfo user, String token, String roles){
        this.user = user;
        this.token = token;
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
