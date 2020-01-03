package model;

import javax.persistence.Entity;

@Entity
public class UtenteApp extends Utente{
	
	private String descrizione;
	private boolean attivo;
	
	
	
	

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
	
	

}
