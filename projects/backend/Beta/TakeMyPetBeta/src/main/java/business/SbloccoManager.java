package business;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import model.Utente;
import utils.JPAUtil;

public class SbloccoManager {

	public Utente sbloccaUtente(String username, String codiceDiSblocco) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente _return = new Utente();
		_return = em.find(Utente.class, username);

		if (_return != null) {

			if (_return.getCodiceSblocco().contentEquals(codiceDiSblocco)) {
				_return.setBloccato(false);
				_return.setContatoreAccessiSbagliati(0);
				_return.setCodiceSblocco(null);

				em.getTransaction().begin();
				em.persist(_return);
				em.getTransaction().commit();

				em.close();
				return _return;
			}

		}

		em.close();
		return null;

	}
}
