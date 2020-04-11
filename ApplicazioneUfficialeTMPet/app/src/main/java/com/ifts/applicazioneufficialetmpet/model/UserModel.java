package com.ifts.applicazioneufficialetmpet.model;

public class UserModel {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public UserModel(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                '}';
    }
}
