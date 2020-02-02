package business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.AdminInterface;
import model.Annuncio;
import model.Evento;
import model.Segnalazione;
import model.UtenteApp;
import model.Valutazione;
import utils.JPAUtil;

public class AdminManager implements AdminInterface {

	@Override
	public List<Segnalazione> visualizzaSegnalazioni() {
		List<Segnalazione> _return = new ArrayList<Segnalazione>();
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		_return = em.createQuery("select c from Segnalazione c").getResultList();
		return _return;
	}

	@Override
	public void rimuoviSegnalazione(Integer idSegnalazione) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		Segnalazione s = em.find(Segnalazione.class, idSegnalazione);
		em.getTransaction().begin();
		em.remove(s);
		em.getTransaction().commit();
	}

	@Override
	public List<Segnalazione> segnalazioniSingoloUtente(String username, boolean controlloTipo) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		List<Segnalazione> _return = new ArrayList<Segnalazione>();

		if (controlloTipo) {
			_return = em.createQuery("select c from segnalazione c where c.segnalatore like :username")
					.setParameter("username", username).getResultList();
		} else {
			_return = em.createQuery("select c from segnalazione c where c.segnalato like :username")
					.setParameter("username", username).getResultList();
		}
		return _return;
	}

	@Override
	public void bloccaUtente(String username) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		UtenteApp u = new UtenteApp();
		u = em.find(UtenteApp.class, username);
		u.setBloccato(true);

		em.getTransaction().begin();
		em.remove(em.find(UtenteApp.class, username));
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void riattivaUtente(String username) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		UtenteApp u = new UtenteApp();
		u = em.find(UtenteApp.class, username);
		u.setBloccato(false);

		em.getTransaction().begin();
		em.remove(em.find(UtenteApp.class, username));
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void eliminaUtente(String username) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		em.getTransaction().begin();
		em.remove(em.find(UtenteApp.class, username));
		em.getTransaction().commit();
	}

	@Override
	public List<UtenteApp> visualizzaUtenti() {
		List<UtenteApp> _return = new ArrayList<UtenteApp>();
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		_return = em.createQuery("select c from utente c where c.tipo != admin").getResultList();
		return _return;
	}

	@Override
	public String visualizzaTestoEvento(Integer idEvento) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		String testo = ((String) em.createQuery("select c.descrizione frome evento c where c = :idEvento")
				.setParameter("idEvento", idEvento).getSingleResult());
		return testo;
	}

	@Override
	public void eliminaEvento(Integer idEvento) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		em.getTransaction().begin();
		em.remove(em.find(Evento.class, idEvento));
		em.getTransaction().commit();
	}

	@Override
	public String visualizzaTestoAnnuncio(Integer idAnnuncio) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		String testo = ((String) em.createQuery("select c.descrizione frome annuncio c where c = :idAnnuncio")
				.setParameter("idAnnuncio", idAnnuncio).getSingleResult());
		return testo;
	}

	@Override
	public void eliminaAnnuncio(Integer idAnnuncio) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		em.getTransaction().begin();
		em.remove(em.find(Annuncio.class, idAnnuncio));
		em.getTransaction().commit();
	}

	@Override
	public String visualizzaTestoValutazione(Integer idValutazione) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		String testo = ((String) em.createQuery("select c.commento frome valutazione c where c = :idValutazione")
				.setParameter("idValutazione", idValutazione).getSingleResult());
		return testo;
	}

	@Override
	public void eliminaValutazione(Integer idValutazione) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		em.getTransaction().begin();
		em.remove(em.find(Valutazione.class, idValutazione));
		em.getTransaction().commit();
	}
	

}
