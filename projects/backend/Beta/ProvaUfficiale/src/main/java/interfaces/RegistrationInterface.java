package interfaces;

import java.util.Date;

public interface RegistrationInterface {
	
	public abstract void registrazione(String tipo, String nome, String cognome, String username, String password, Date dataDiNascita, Date dataDiRegistrazione, String descrizione, String latitudine, String longitudine);
	
	

	
}
