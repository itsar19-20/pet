package business;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.ProprietarioInterface;
import model.Animale;
import model.Evento;
import model.Proprietario;
import model.Segnalazione;
import model.UtenteApp;
import model.Valutazione;
import utils.JPAUtil;

public class ProprietarioManager extends UtenteAppManager implements ProprietarioInterface {

	public void modificaProfilo(String username) {
		// TODO Auto-generated method stub
		
	}

	public UtenteApp cambiaTipoProfilo(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public void partecipaEvento(Evento evento, UtenteApp partecipante) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	//Animali
	public List<Animale> visualizzaAnimale(Proprietario utente) {
		List<Animale> _return= new ArrayList<Animale>();
	
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		//_return = em.createNamedQuery("cercaAnimaliPerProprietario", Animale.class).setParameter("username", utente).getResultList();
		
		for (Animale e : em.createNamedQuery("cercaAnimaliPerProprietario", Animale.class).setParameter("username", utente).getResultList())  {
			_return.add(e);
		}
		
		
		
		em.close();
		return _return;
	}

	

	@Override
	public void eliminaAnimale(Integer idAnimale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String aggiungiAnimale(Date dataDiNascita, String dettagli, Integer eta, String nome, String razza,
			String tipo) {
EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		Animale a= new Animale();
		a.setDataDiNascita(dataDiNascita);
		a.setDettagli(dettagli);
		a.setEta(eta);
		a.setNome(nome);
		a.setRazza(razza);
		a.setTipo(tipo);
		
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		em.close();
		return null;
	}





}
