package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
	   
    @NamedQuery(name="annuncio.findByProprietario",
                query="SELECT c FROM Annuncio c WHERE c.proprietarioAnnuncio = :name"),
    @NamedQuery(name="annuncio.findAll",
    			query="SELECT c FROM Annuncio c"),
})
public class Annuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_annuncio;
	@OneToOne
	private Proprietario proprietarioAnnuncio;
	@OneToMany
	private List<PetSitter> petSittersAnnuncio;
	@OneToMany
	private List<Animale> animaliAnnuncio;
	private String descrizione;
	private String latitudine;
	private String longitudine;
	
	
	
	public int getId_annuncio() {
		return id_annuncio;
	}
	public void setId_annuncio(int id_annuncio) {
		this.id_annuncio = id_annuncio;
	}
	public Proprietario getProprietarioAnnuncio() {
		return proprietarioAnnuncio;
	}
	public void setProprietarioAnnuncio(Proprietario proprietarioAnnuncio) {
		this.proprietarioAnnuncio = proprietarioAnnuncio;
	}
	public List<PetSitter> getPetSittersAnnuncio() {
		return petSittersAnnuncio;
	}
	public void setPetSittersAnnuncio(List<PetSitter> petSittersAnnuncio) {
		this.petSittersAnnuncio = petSittersAnnuncio;
	}
	public List<Animale> getAnimaliAnnuncio() {
		return animaliAnnuncio;
	}
	public void setAnimaliAnnuncio(List<Animale> animaliAnnuncio) {
		this.animaliAnnuncio = animaliAnnuncio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}
	public String getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}
	
	
	

}
