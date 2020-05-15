package com.ifts.applicazioneufficialetmpet.model;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class EventoModel {

@SerializedName(value="id_evento")
    private int id_Evento;



    private String nomeEvento;
    private String descrizione;

    private Date dataEvento;

    private UserModel organizzatore;
    private List<UserModel> partecipanti;


    private String urlImmagineEvento;

    @Override
    public String toString() {
        return "EventoModel{" +
                "id_Evento=" + id_Evento +
                ", nomeEvento='" + nomeEvento + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", dataEvento=" + dataEvento +
                ", organizzatore=" + organizzatore +
                ", partecipanti=" + partecipanti +
                ", urlImmagineEvento='" + urlImmagineEvento + '\'' +
                ", immagineEvento=" + immagineEvento +
                '}';
    }

    private ImmagineModel immagineEvento;


    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public UserModel getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(UserModel organizzatore) {
        this.organizzatore = organizzatore;
    }

    public List<UserModel> getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(List<UserModel> partecipanti) {
        this.partecipanti = partecipanti;
    }
    public ImmagineModel getImmagineEvento() {
        return immagineEvento;
    }

    public void setImmagineEvento(ImmagineModel immagineEvento) {
        this.immagineEvento = immagineEvento;
    }

    public int getId_Evento() {
        return id_Evento;
    }

    public void setId_Evento(int id_Evento) {
        this.id_Evento = id_Evento;
    }
    public String getUrlImmagineEvento() {
        return urlImmagineEvento;
    }

    public void setUrlImmagineEvento(String urlImmagineEvento) {
        this.urlImmagineEvento = urlImmagineEvento;
    }
}
