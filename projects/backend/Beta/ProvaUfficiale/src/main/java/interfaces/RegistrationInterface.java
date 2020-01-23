package interfaces;

import java.util.Date;

public interface RegistrationInterface {
	
	public abstract String registrazione(String tipo, String nome, String cognome, String username, String password, Date dataDiNascita, Date dataDiRegistrazione, String descrizione, String latitudine, String longitudine, Date dataUltimoLogin);
	
	

	
}
