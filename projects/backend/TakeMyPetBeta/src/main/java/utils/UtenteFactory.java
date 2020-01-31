package utils;

import model.Admin;
import model.PetSitter;
import model.Proprietario;
import model.Utente;

public class UtenteFactory {
	
	public static Utente creaUtente(String tipoUtente) {
		Utente _return = null;
		
		if (tipoUtente.equals("admin")) {
			_return = new Admin();
		}
		else if (tipoUtente.equals("proprietario")) {
			_return = new Proprietario();
		}
		else if (tipoUtente.equals("petsitter")) {
			_return = new PetSitter();
		}
		
		return _return;
	}

}
