package com.ifts.applicazioneufficialetmpet.model;

public class ImmagineModel {
    private Integer id_Immagine;
    private String urlImmagine;

    @Override
    public String toString() {
        return "ImmagineModel{" +
                "id_Immagine=" + id_Immagine +
                ", urlImmagine='" + urlImmagine + '\'' +
                '}';
    }

    public Integer getId_Immagine() {
        return id_Immagine;
    }

    public void setId_Immagine(Integer id_Immagine) {
        this.id_Immagine = id_Immagine;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }
}
