package interfaces;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import model.Utente;

public interface LoginInterface {
	
	abstract Utente login(String username, String password) throws AddressException, MessagingException;



}
