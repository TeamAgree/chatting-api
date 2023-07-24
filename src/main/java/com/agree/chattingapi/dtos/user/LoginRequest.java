package com.agree.chattingapi.dtos.user;

public class LoginRequest {

    private int userSeq;
    private String id;
    private String pw;
    private String name;
    private String status;

    public LoginRequest() {}

    public LoginRequest(String id){
        this.id = id;
    }

    public LoginRequest(int userSeq, String id, String pw, String name, String status){
        this.userSeq = userSeq;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.status = status;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
