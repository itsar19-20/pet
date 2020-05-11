package com.ifts.applicazioneufficialetmpet.model;
import java.util.Date;
import java.util.List;

public class EventoModel {


    private String nomeEvento;
    private Date dataEvento;
    private String descrizione;

    private UserModel organizzatore;
    private List<UserModel> partecipanti;
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

    @Override
    public String toString() {
        return "EventoModel{" +
                "nomeEvento='" + nomeEvento + '\'' +
                ", dataEvento=" + dataEvento +
                ", descrizione='" + descrizione + '\'' +
                ", organizzatore=" + organizzatore +
                ", partecipanti=" + partecipanti +
                ", immagineEvento=" + immagineEvento +
                '}';
    }
}
