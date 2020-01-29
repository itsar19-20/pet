package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Preferiti {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_preferiti;
	@OneToOne
	private Annuncio annuncio;
	@OneToOne
	private PetSitter petsitter;
	
	
	
	
	public int getId_preferiti() {
		return id_preferiti;
	}
	public void setId_preferiti(int id_preferiti) {
		this.id_preferiti = id_preferiti;
	}
	public Annuncio getAnnuncio() {
		return annuncio;
	}
	public void setAnnuncio(Annuncio annuncio) {
		this.annuncio = annuncio;
	}
	public PetSitter getPetsitter() {
		return petsitter;
	}
	public void setPetsitter(PetSitter petsitter) {
		this.petsitter = petsitter;
	}
	
	

}
