package business;

import java.util.Date;

import javax.persistence.EntityManager;

import interfaces.LoginInterface;
import model.Utente;
import model.UtenteApp;
import utils.JPAUtil;

public class LoginManager implements LoginInterface {

	public Utente login(String username, String password) {

		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente u = em.find(Utente.class, username);

		if (u != null) {

			em.getTransaction().begin();
			u.setDataOraUltimoLogin(new Date());
			em.remove(em.find(Utente.class, username));
			em.persist(u);
			em.getTransaction().commit();

			if (u.getPassword().contentEquals(password) && !((UtenteApp) u).isBloccato()) {
				u.setContatoreAccessiSbagliati(0);
				((UtenteApp) u).setAttivo(true);
				em.getTransaction().begin();
				em.remove(em.find(Utente.class, username));
				em.persist(u);
				em.getTransaction().commit();
				
				return u;
			}
		}

//		Integer i = u.getContatoreAccessiSbagliati();
//		i++;
//		u.setContatoreAccessiSbagliati(i);
//		em.getTransaction().begin();
//		em.remove(em.find(Utente.class, username));
//		em.persist(u);
//		em.getTransaction().commit();
		return null;

	}

}
