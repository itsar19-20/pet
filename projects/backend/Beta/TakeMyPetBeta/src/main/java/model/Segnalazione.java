package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Segnalazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_segnalazione;
	@OneToOne
	private UtenteApp segnalato;
	@OneToOne
	private UtenteApp segnalatore;
	@OneToOne
	private Annuncio annuncio;
	@OneToOne
	private Evento evento;
	private String descrizione;
	
	
	
	
	public int getId_segnalazione() {
		return id_segnalazione;
	}
	public void setId_segnalazione(int id_segnalazione) {
		this.id_segnalazione = id_segnalazione;
	}
	public UtenteApp getSegnalato() {
		return segnalato;
	}
	public void setSegnalato(UtenteApp segnalato) {
		this.segnalato = segnalato;
	}
	public UtenteApp getSegnalatore() {
		return segnalatore;
	}
	public void setSegnalatore(UtenteApp segnalatore) {
		this.segnalatore = segnalatore;
	}
	public Annuncio getAnnuncio() {
		return annuncio;
	}
	public void setAnnuncio(Annuncio annuncio) {
		this.annuncio = annuncio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	

}
