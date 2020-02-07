package business;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.LoginController;
import interfaces.LoginInterface;
import model.Utente;
import model.UtenteApp;
import utils.JPAUtil;

public class LoginManager implements LoginInterface {
	private static Logger log = LoggerFactory.getLogger(LoginManager.class);
	
	public Utente login(String username, String password) {

		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente u = em.find(Utente.class, username);
		
		log.debug("LoginManager Pronto");

		if (u != null) {
			u.setDataOraUltimoLogin(new Date());
			em.getTransaction().begin();
			em.remove(em.find(Utente.class, username));
			em.persist(u);
			em.getTransaction().commit();

			if (u.getPassword().contentEquals(password)) { //&& !((UtenteApp) u).isBloccato()) {
				u.setContatoreAccessiSbagliati(0);
				//((UtenteApp) u).setAttivo(true);
				em.getTransaction().begin();
				em.remove(em.find(Utente.class, username));
				em.persist(u);
				em.getTransaction().commit();
				log.debug("LoginManager Funziona");
				log.debug("Utente trovato");
				return u;
				
			} 
			
			//else {
				//Integer i = u.getContatoreAccessiSbagliati();
				//i++;
				//u.setContatoreAccessiSbagliati(i);
				//em.getTransaction().begin();
				//em.remove(em.find(Utente.class, username));
				//em.persist(u);
				//em.getTransaction().commit();
				//return null;
			//}
		}
		log.debug("LoginManager Funziona");
		log.debug("Utente NON trovato");
		return null;

	}

}
