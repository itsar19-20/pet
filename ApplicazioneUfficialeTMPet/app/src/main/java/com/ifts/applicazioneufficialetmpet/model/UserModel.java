package com.ifts.applicazioneufficialetmpet.model;

public class UserModel {

    private String username,
                    nome,
                    cognome,
                    tipoUtente ;

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", tipoUtente='" + tipoUtente + '\'' +
                '}';
    }

    public String getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(String tipoUtente) {
        this.tipoUtente = tipoUtente;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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
