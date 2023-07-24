package com.agree.chattingapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "token_info")
public class TokenInfo extends CommonEntity{

    @Id
    @Column(name = "user_id", nullable = false, length = 15)
    private String id;

    @Column(name = "token", columnDefinition = "TEXT")
    private String token;

    @Column(name = "roles", nullable = false)
    private String roles;

    public TokenInfo() {}
    public TokenInfo(String id, String token, String roles){
        this.id = id;
        this.token = token;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
