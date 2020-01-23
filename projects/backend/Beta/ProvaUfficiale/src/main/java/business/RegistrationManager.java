package business;

import java.util.Date;

import javax.persistence.EntityManager;

import interfaces.RegistrationInterface;
import model.Admin;
import model.PetSitter;
import model.Proprietario;
import model.Utente;
import model.UtenteApp;
import utils.JPAUtil;
import utils.UtenteFactory;

public class RegistrationManager implements RegistrationInterface {

	public String registrazione(String tipo, String nome, String cognome, String username, String password,
			Date dataDiNascita, Date dataRegistrazione, String descrizione, String latitudine, String longitudine,
			Date dataUltimoLogin) {

		Utente u = UtenteFactory.creaUtente(tipo);
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		if (em.find(Utente.class, username) == null) {

			if (u instanceof Admin) {
				u.setNome(nome);
				u.setCognome(cognome);
				u.setUsername(username);
				u.setPassword(password);
				u.setDataDiNascita(dataDiNascita);
				u.setDataRegistrazione(dataRegistrazione);
			}

			if (u instanceof PetSitter || u instanceof Proprietario) {
				u.setNome(nome);
				u.setCognome(cognome);
				u.setUsername(username);
				u.setPassword(password);
				u.setDataDiNascita(dataDiNascita);
				u.setDataRegistrazione(dataRegistrazione);
				((UtenteApp) u).setDescrizione(descrizione);
				((UtenteApp) u).setLatitudine(latitudine);
				((UtenteApp) u).setLongitudine(longitudine);
				((UtenteApp) u).setBloccato(false);
				((UtenteApp) u).setAttivo(true);
			}

			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			em.close();
			return null;
		} 
		else {
			String str = "L'username esiste già, scegline un altro.";
			return str;
		}
	}

}
