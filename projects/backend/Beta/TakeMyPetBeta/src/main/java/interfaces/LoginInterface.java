package interfaces;

import model.Utente;

public interface LoginInterface {
	
	abstract Utente login(String username, String password);



}
