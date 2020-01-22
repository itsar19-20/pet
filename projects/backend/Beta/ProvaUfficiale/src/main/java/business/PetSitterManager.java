package business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.PetSitterInterface;
import model.Evento;
import model.Utente;
import model.UtenteApp;
import utils.JPAUtil;

public abstract class PetSitterManager implements PetSitterInterface {

	public List<Evento> visualizzaEventiUtente(UtenteApp utente) {
		List<Evento> _return= new ArrayList();
	
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		_return = em.createNamedQuery("cercaEventiPerOrganizzatore", Evento.class).setParameter("username", utente).getResultList();
		
		for (Evento e : em.createNamedQuery("cercaEventiPerPartecipante", Evento.class).setParameter("username", utente).getResultList())  {
			_return.add(e);
		}
		
		
		
		em.close();
		return _return;
	}
		
	public List<Evento> visualizzaTuttiEventi(){
		List<Evento> _return= new ArrayList();
		
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		_return=em.createQuery("select c from evento",Evento.class).getResultList();
		em.close();
		return _return;
		
	}
		
	}


