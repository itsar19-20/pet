package com.ifts.applicazioneufficialetmpet.model;

import java.util.Date;
import java.util.List;

public class AnnuncioModel {


    private int id_annuncio;

    Proprietario proprietarioAnnuncio;
    String usernameProprietario;

    String nomeAnnuncio,
            descrizione,
            urlImmagineAnnuncio;

    
    private Date dataAnnuncio;

    private List<UserModel> petSitterAnnuncio;

    public int getId_annuncio() {
        return id_annuncio;
    }

    public void setId_annuncio(int id_annuncio) {
        this.id_annuncio = id_annuncio;
    }

    public String getNomeAnnuncio() {
        return nomeAnnuncio;
    }

    public void setNomeAnnuncio(String nomeAnnuncio) {
        this.nomeAnnuncio = nomeAnnuncio;
    }

    public String getUsernameProprietario() {
        return usernameProprietario;
    }

    public void setUsernameProprietario(String usernameProprietario) {
        this.usernameProprietario = usernameProprietario;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrlImmagineAnnuncio() {
        return urlImmagineAnnuncio;
    }

    public void setUrlImmagineAnnuncio(String urlImmagineAnnuncio) {
        this.urlImmagineAnnuncio = urlImmagineAnnuncio;
    }

    public Date getDataAnnuncio() {
        return dataAnnuncio;
    }

    public void setDataAnnuncio(Date dataAnnuncio) {
        this.dataAnnuncio = dataAnnuncio;
    }

    public List<UserModel> getPetSitterAnnuncio() {
        return petSitterAnnuncio;
    }

    public void setPetSitterAnnuncio(List<UserModel> petSitterAnnuncio) {
        this.petSitterAnnuncio = petSitterAnnuncio;
    }



    public Proprietario getProprietario() {
        return proprietarioAnnuncio;
    }




    @Override
    public String toString() {
        return "AnnuncioModel{" +
                "id_Annuncio=" + id_annuncio +
                ", nomeAnnuncio='" + nomeAnnuncio + '\'' +
                ", usernameProprietario='" + usernameProprietario + '\'' +
                ", descrzioneProprietario='" + descrizione + '\'' +
                ", urlImmagineAnnucio='" + urlImmagineAnnuncio + '\'' +
                ", dataCreazione_annuncio=" + dataAnnuncio +
                ", petSitterAnnuncio=" + petSitterAnnuncio +
                '}';
    }

}
