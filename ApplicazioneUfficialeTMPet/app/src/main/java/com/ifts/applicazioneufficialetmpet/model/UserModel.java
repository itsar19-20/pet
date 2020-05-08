package com.ifts.applicazioneufficialetmpet.model;

public class UserModel {


    private String username,
                    nome,
                    cognome,
                    tipoUtente,
                    descrizione;
    private ImmagineModel immagineProfilo;

    public ImmagineModel getImmagineProfilo() {
        return immagineProfilo;
    }

    public void setImmagineProfilo(ImmagineModel immagineProfilo) {
        this.immagineProfilo = immagineProfilo;
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
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", tipoUtente='" + tipoUtente + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", immagineProfilo=" + immagineProfilo +
                '}';
    }
}
