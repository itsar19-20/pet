package business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.AdminInterface;
import model.Segnalazione;
import model.Utente;
import utils.JPAUtil;

public class AdminManager implements AdminInterface {

	public List<Segnalazione> visualizzaSegnalazioni() {
		List<Segnalazione> _return = new ArrayList();
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		List lista = em.createQuery("selct c from segnalazione c").getResultList();
		for (Segnalazione s : em.createQuery("select c from c", Segnalazione.class).getResultList()) {
			_return.add(s);
		}
		return _return;
	}
	
	public void riattivaUtentiBloccati(String username) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		//Utente u = em.find(Utenti.class, attivo);
		//setto attivo a true
	}

}
