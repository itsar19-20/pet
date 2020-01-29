package model;

import javax.persistence.Entity;

@Entity
public class UtenteApp extends Utente{
	
	private String descrizione;
	private boolean attivo;
	private boolean bloccato;
	private String latitudine;
	private String longitudine;
	

	public UtenteApp() {
	}
	
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

	public boolean isBloccato() {
		return bloccato;
	}

	public void setBloccato(boolean bloccato) {
		this.bloccato = bloccato;
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

	@Override
	public String toString() {
		return "UtenteApp [descrizione=" + descrizione + ", attivo=" + attivo + ", bloccato=" + bloccato
				+ ", latitudine=" + latitudine + ", longitudine=" + longitudine + "]";
	}
	
	

	
	
	

}
