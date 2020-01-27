package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Preferiti {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private PetSitter preferitoDelPetSitter;
	@OneToMany
	private List<Annuncio> annunciInPreferiti;
	
	@OneToMany
	private List<PetSitter> petSitterInPreferiti;
	@OneToOne
	private Proprietario preferitoDelProprietario;
}
