package com.ifts.applicazioneufficialetmpet.model;

public class PreferitoModel {
    private int id;
    private PetSitter preferitoDelPetSitter;

    //preferiti del proprietario
    private Proprietario preferitoDelProprietario;
    private PetSitter petSitterPreferitoDelProprietario;

    private AnnuncioModel annuncioPreferito;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PetSitter getPreferitoDelPetSitter() {
        return preferitoDelPetSitter;
    }

    public void setPreferitoDelPetSitter(PetSitter preferitoDelPetSitter) {
        this.preferitoDelPetSitter = preferitoDelPetSitter;
    }

    public Proprietario getPreferitoDelProprietario() {
        return preferitoDelProprietario;
    }

    public void setPreferitoDelProprietario(Proprietario preferitoDelProprietario) {
        this.preferitoDelProprietario = preferitoDelProprietario;
    }

    public PetSitter getPetSitterPreferitoDelProprietario() {
        return petSitterPreferitoDelProprietario;
    }

    public void setPetSitterPreferitoDelProprietario(PetSitter petSitterPreferitoDelProprietario) {
        this.petSitterPreferitoDelProprietario = petSitterPreferitoDelProprietario;
    }

    public AnnuncioModel getAnnuncioPreferito() {
        return annuncioPreferito;
    }

    public void setAnnuncioPreferito(AnnuncioModel annuncioPreferito) {
        this.annuncioPreferito = annuncioPreferito;
    }


}
