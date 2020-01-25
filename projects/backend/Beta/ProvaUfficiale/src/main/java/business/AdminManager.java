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

	//VISUALIZZA SEGNALAZIONI
	public List<Segnalazione> visualizzaSegnalazioni() {
		List<Segnalazione> _return = new ArrayList<Segnalazione>();
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		for (Segnalazione s : em.createQuery("select c from segnalazione c", Segnalazione.class).getResultList()) {
			_return.add(s);
		}
		return _return;
	}
	
	//BLOCCA/SBLOCCA UTENTI
	public void bloccaSbloccaUtenti(String username) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		UtenteApp utenteApp = em.find(UtenteApp.class, username);
		if (utenteApp.isBloccato()) {
			utenteApp.setBloccato(true);
		} else {
			utenteApp.setBloccato(false);
		}
		
		em.getTransaction().begin();
		em.refresh(utenteApp);
		em.getTransaction().commit();
		em.close();
	}
	
	//ELIMANA UTENTE
	public void eliminaUtenti(String username) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		UtenteApp utenteApp = em.find(UtenteApp.class, username);
		
		em.getTransaction();
		em.remove(utenteApp);
		em.close();
	}
	
	//RIMUOVI ANNUNCIO
	public void rimuoviAnnuncio(String id_annuncio) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Annuncio annuncio = em.find(Annuncio.class, id_annuncio);
		
		em.getTransaction();
		em.remove(annuncio);
		em.close();
	}
	
	//RIMUOVI VALUTAZIONE
		public void rimuoviValutazione(String id_valutazione) {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			Valutazione valutazione= em.find(Valutazione.class, id_valutazione);
			valutazione.getCommento();
			
			em.getTransaction();
			em.remove(valutazione);
			em.close();
		}
		
	//VISUALIZZA DESCRIZIONE EVENTO
		public String visualizzaDescrizioneEvento(String id_evento) {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			Evento evento = em.find(Evento.class, id_evento);
			String _return = evento.getDescrizione();
			return _return;
			
		}
}
