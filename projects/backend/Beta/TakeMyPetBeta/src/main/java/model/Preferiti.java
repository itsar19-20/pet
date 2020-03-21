package model;

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
	   
    @NamedQuery(name="preferiti.findByProprietario",
                query="SELECT c FROM Preferiti c WHERE c.preferitoDelProprietario = :name"),
    @NamedQuery(name="preferiti.findByPetSitter",
    query="SELECT c FROM Preferiti c WHERE c.preferitoDelPetSitter = :name"),
})
public class Preferiti {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@OneToOne
	private PetSitter preferitoDelPetSitter;
	
	
	
	@OneToOne
	private Annuncio annuncioPreferito;
	
	
	
	@OneToOne
	private PetSitter petSitterPreferitoDelProprietario;
	@OneToOne
	private Proprietario preferitoDelProprietario;
	
	
	
	
	
	
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
	public int getId() {
		return id;
	}
	public Annuncio getAnnuncioPreferito() {
		return annuncioPreferito;
	}
	public void setAnnuncioPreferito(Annuncio annuncioPreferito) {
		this.annuncioPreferito = annuncioPreferito;
	}
	public PetSitter getPetSitterPreferitoDelProprietario() {
		return petSitterPreferitoDelProprietario;
	}
	public void setPetSitterPreferitoDelProprietario(PetSitter petSitterPreferitoDelProprietario) {
		this.petSitterPreferitoDelProprietario = petSitterPreferitoDelProprietario;
	}
	

	
	
}
