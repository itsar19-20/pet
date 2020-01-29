package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class UtenteApp extends Utente{
	
	private String descrizione;
	private boolean attivo;
	private boolean bloccato;
	@OneToMany
	private List<Preferiti> preferiti;
	
	
	
	

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public List<Preferiti> getPreferiti() {
		return preferiti;
	}

	public void setPreferiti(List<Preferiti> preferiti) {
		this.preferiti = preferiti;
	}

	public boolean isBloccato() {
		return bloccato;
	}

	public void setBloccato(boolean bloccato) {
		this.bloccato = bloccato;
	}
	
	

}
