package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Email {

	@Id
	private String email;
	@ManyToOne
	private Utente utente_email;
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Utente getUtente_email() {
		return utente_email;
	}

	public void setUtente_email(Utente utente_email) {
		this.utente_email = utente_email;
	}
	
}
