package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Valutazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_valutazione;
	@OneToOne
	private UtenteApp valutatore;
	@OneToOne
	private UtenteApp valutato;
	private numeroOrme numeroOrme;
	private String commento;
	
	
	
	public int getId_valutazione() {
		return id_valutazione;
	}
	public void setId_valutazione(int id_valutazione) {
		this.id_valutazione = id_valutazione;
	}
	public UtenteApp getValutatore() {
		return valutatore;
	}
	public void setValutatore(UtenteApp valutatore) {
		this.valutatore = valutatore;
	}
	public UtenteApp getValutato() {
		return valutato;
	}
	public void setValutato(UtenteApp valutato) {
		this.valutato = valutato;
	}
	public numeroOrme getNumeroOrme() {
		return numeroOrme;
	}
	public void setNumeroOrme(numeroOrme numeroOrme) {
		this.numeroOrme = numeroOrme;
	}
	public String getCommento() {
		return commento;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}
	
	

}
