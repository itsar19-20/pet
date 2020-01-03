package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_evento;
	@OneToOne
	private UtenteApp organizzatore;
	@OneToMany
	private List<UtenteApp> partecipanti;
	private String descrizione;
	
	
	
	
	public int getId_evento() {
		return id_evento;
	}
	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}
	public UtenteApp getOrganizzatore() {
		return organizzatore;
	}
	public void setOrganizzatore(UtenteApp organizzatore) {
		this.organizzatore = organizzatore;
	}
	public List<UtenteApp> getPartecipanti() {
		return partecipanti;
	}
	public void setPartecipanti(List<UtenteApp> partecipanti) {
		this.partecipanti = partecipanti;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
