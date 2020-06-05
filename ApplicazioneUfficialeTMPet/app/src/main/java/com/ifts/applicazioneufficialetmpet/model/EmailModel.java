package com.ifts.applicazioneufficialetmpet.model;

public class EmailModel {
    private String email;

    @Override
    public String toString() {
        return "EmailModel{" +
                "email='" + email + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
