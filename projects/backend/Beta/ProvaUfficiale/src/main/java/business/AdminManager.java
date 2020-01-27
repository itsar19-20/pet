package business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.AdminInterface;
import model.Annuncio;
import model.Evento;
import model.Segnalazione;
import model.Utente;
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
	
	//GESTIONE UTENTI
	public List<Utente> gestioneUtenti(){
		List<Utente> _return = new ArrayList <Utente>();
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		for (Utente utente : em.createQuery("select c from utente c", Utente.class).getResultList()) {
			_return.add(utente);
		}
			return _return;
	}
	
	//LISTA SEGNALAZIONI SINGOLO UTENTE
	public List<Segnalazione> visualizzaSegnalazioniSingoloUtente(String username) {
		List<Segnalazione> _return = new ArrayList<Segnalazione>();
		EntityManager em_segnalazione = JPAUtil.getInstance().getEmf().createEntityManager();
		EntityManager em_username = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente utente = em_username.find(Utente.class, username);
		
		for (Segnalazione s : em_segnalazione.createQuery("select id_username from segnalazione c", Segnalazione.class).getResultList()) {
			//if (s==utente) {
			_return.add(s);
		}
		return _return;
		}
//	}
	
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
		
	//
}
