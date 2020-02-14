package model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class UtenteApp extends Utente{
	
	@OneToOne
	private Immagine immagineProfilo;
	private String descrizione;
	private boolean attivo;
	
	private String latitudine;
	private String longitudine;
	private boolean doppioProfilo;
	

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
		return "UtenteApp [descrizione=" + descrizione + ", attivo=" + attivo +  ", latitudine=" + latitudine + ", longitudine=" + longitudine + "]";
	}

	public boolean isDoppioProfilo() {
		return doppioProfilo;
	}

	public void setDoppioProfilo(boolean doppioProfilo) {
		this.doppioProfilo = doppioProfilo;
	}

	public Immagine getImmagineProfilo() {
		return immagineProfilo;
	}

	public void setImmagineProfilo(Immagine immagineProfilo) {
		this.immagineProfilo = immagineProfilo;
	}
	
	

	
	
	

}
