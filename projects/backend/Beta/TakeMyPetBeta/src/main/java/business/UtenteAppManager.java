
package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.UtenteAppInterface;
import model.Annuncio;
import model.Evento;
import model.Immagine;
import model.Segnalazione;
import model.UtenteApp;
import model.Valutazione;
import utils.JPAUtil;

public class UtenteAppManager implements UtenteAppInterface {
	
	
	//Eventi

	public List<Evento> visualizzaEventiUtente(String username) {
		List<Evento> _return= new ArrayList<Evento>();
	
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		_return = em.createNamedQuery("cercaEventiPerOrganizzatore", Evento.class).setParameter("username", username).getResultList();
		
		for (Evento e : em.createNamedQuery("cercaEventiPerPartecipante", Evento.class).setParameter("username", username).getResultList())  {
			_return.add(e);
		}
		
		
		
		em.close();
		return _return;
	}
		
	public List<Evento> visualizzaTuttiEventi(){
		List<Evento> _return= new ArrayList<Evento>();
		
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		_return=em.createQuery("select c from evento",Evento.class).getResultList();
		em.close();
		return _return;
		
	}

	public void nuovoEvento(String nomeEvento,  Date dataEvento, String descrizione, String latitudine, String longitudine, String usernameOrganizzatore) {
		
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		Evento e = new Evento();
		
		e.setNomeEvento(nomeEvento);
		e.setDataEvento(dataEvento);
		e.setDescrizione(descrizione);
		e.setLatitudine(latitudine);
		e.setLongitudine(longitudine);
		e.setOrganizzatore(em.find(UtenteApp.class, usernameOrganizzatore));
		
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	public void partecipaEvento(int id_evento, String usernamePartecipante) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Evento e = new Evento();
		UtenteApp partecipante = new UtenteApp();
		List<UtenteApp> listaPartecipanti = new ArrayList<UtenteApp>();
		
		partecipante = em.find(UtenteApp.class, usernamePartecipante);
		e = em.find(Evento.class, id_evento);
		
		listaPartecipanti = e.getPartecipanti();
		listaPartecipanti.add(partecipante);
		e.setPartecipanti(listaPartecipanti);
		
		 em.getTransaction().begin();
		 em.refresh(e);
		 em.getTransaction().commit();
			em.close();
		
	}
	
	public void eliminaEvento(Integer idEvento) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		em.getTransaction().begin();
		em.remove(em.find(Evento.class, idEvento));
		em.getTransaction().commit();
		em.close();
	
	}
	
	
	//Profilo
	
	public UtenteApp visualizzaProfilo(String username) {
		
		UtenteApp _return = new UtenteApp();
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		_return = em.find(UtenteApp.class, username);
		em.close();
		return _return;
	}
	
	
	//Immagine profilo
	
	public void inserisciImmagine(String username, String urlImmagine) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Immagine ima = new Immagine();
		UtenteApp utente = new UtenteApp();
		
		utente = em.find(UtenteApp.class, username);
		Immagine vecchiaImmagine = new Immagine();
		if ((vecchiaImmagine = em.find(Immagine.class, utente.getImmagineProfilo().getId_Immagine())) != null) {
			em.remove(vecchiaImmagine);
		}
		ima.setUrlImmagine(urlImmagine);
		utente.setImmagineProfilo(ima);
		
		em.getTransaction().begin();
		em.persist(ima);
		em.remove(utente);
		em.persist(utente);
		em.getTransaction().commit();
	}
	
	
	//Segnalazioni
	public void inviaSegnalazione(UtenteApp segnalato, UtenteApp segnalatore, String descrizione, Annuncio annuncio, Evento evento) {
		
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Segnalazione s = new Segnalazione();
		
		s.setAnnuncio(annuncio);
		s.setEvento(evento);
		s.setDescrizione(descrizione);
		s.setSegnalato(segnalatore);
		s.setSegnalatore(segnalatore);
		
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
		
	}
	
	//Valutazioni
	
	public void inviaValutazione(UtenteApp  valutato, UtenteApp valutatore,String commento,float numeroOrme) {
		EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		Valutazione  v= new Valutazione ();
		v.setValutato(valutato);
		v.setValutatore(valutatore);
		v.setCommento(commento);
		v.setNumeroOrme(numeroOrme);
		
		 em.getTransaction().begin();
		 em.persist(v);
		
		 em.getTransaction().commit();
		 em.close();
		
	}
	
	public Annuncio visualizzaAnnuncio(Integer idAnnuncio) {
		EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		
		Annuncio annuncio=new Annuncio();
		annuncio=em.find(Annuncio.class, idAnnuncio);
		
		
		return annuncio;
		
		
	}

}
