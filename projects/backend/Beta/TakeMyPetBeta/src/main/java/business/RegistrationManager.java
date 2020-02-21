package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.RegistrationInterface;
import model.Admin;
import model.Email;
import model.PetSitter;
import model.Proprietario;
import model.Utente;
import model.UtenteApp;
import utils.JPAUtil;
import utils.UtenteFactory;

public class RegistrationManager implements RegistrationInterface {


	public String registrazione(boolean doppioProfilo, String tipo, String nome, String cognome,String email, String username, String password,
			Date dataDiNascita, Date dataRegistrazione, String descrizione, String latitudine, String longitudine,
			Date dataUltimoLogin) {

		Utente u = UtenteFactory.creaUtente(tipo);
		List<Email> emails = new ArrayList<>();
		Email mail = new Email();
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		if (em.find(Utente.class, username) == null) {

			if (u instanceof Admin) {
				u.setTipoUtente("admin");
				u.setNome(nome);
				u.setCognome(cognome);
				u.setUsername(username);
				u.setPassword(password);
				u.setDataDiNascita(dataDiNascita);
				u.setDataRegistrazione(dataRegistrazione);
				mail.setEmail(email);
				mail.setUtente_email(u);
				emails.add(mail);
				u.setEmails(emails);
			}

			if (u instanceof PetSitter) {
				u.setTipoUtente("petsitter");
				u.setNome(nome);
				u.setCognome(cognome);
				u.setUsername(username);
				u.setPassword(password);
				u.setDataDiNascita(dataDiNascita);
				u.setDataRegistrazione(dataRegistrazione);
				mail.setEmail(email);
				mail.setUtente_email(u);
				emails.add(mail);
				u.setEmails(emails);
				((UtenteApp) u).setDescrizione(descrizione);
				((UtenteApp) u).setLatitudine(latitudine);
				((UtenteApp) u).setLongitudine(longitudine);
				((UtenteApp) u).setBloccato(false);
				((UtenteApp) u).setAttivo(true);
				((UtenteApp) u).setDoppioProfilo(doppioProfilo);
				
				/*
				if(doppioProfilo) {
					Proprietario u2 = (Proprietario) u;
					u2.setTipoUtente("proprietario");
					em.getTransaction().begin();
					em.persist(u2);
					em.getTransaction().commit();
					
				}
				*/
			}
			
			if(u instanceof Proprietario) {
				u.setTipoUtente("proprietario");
				u.setNome(nome);
				u.setCognome(cognome);
				u.setUsername(username);
				u.setPassword(password);
				u.setDataDiNascita(dataDiNascita);
				u.setDataRegistrazione(dataRegistrazione);
				mail.setEmail(email);
				mail.setUtente_email(u);
				emails.add(mail);
				u.setEmails(emails);
				((UtenteApp) u).setDescrizione(descrizione);
				((UtenteApp) u).setLatitudine(latitudine);
				((UtenteApp) u).setLongitudine(longitudine);
				((UtenteApp) u).setBloccato(false);
				((UtenteApp) u).setAttivo(true);
				((UtenteApp) u).setDoppioProfilo(doppioProfilo);
				
				//IL PROPRIETARIO E' AUTOMATICAMENTE UN PET SITTER!
				
			}

			em.getTransaction().begin();
			em.persist(u);
			em.persist(mail);
			em.getTransaction().commit();
			em.close();
			return null;
		} 
		else {
			String str = "L'username esiste gia, scegline un altro.";
			return str;
		}
	}

}
