package com.ifts.applicazioneufficialetmpet.model;

public class UserModel {

    private String username,
                    tipoUtente ;

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", tipoUtente='" + tipoUtente + '\'' +
                '}';
    }

    public String getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(String tipoUtente) {
        this.tipoUtente = tipoUtente;
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public UserModel(String username) {
        this.username = username;
    }

}
