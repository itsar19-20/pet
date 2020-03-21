
package business;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.PetSitterInterface;
import model.Annuncio;
import model.PetSitter;
import model.Preferiti;
import utils.JPAUtil;

public  class PetSitterManager extends UtenteAppManager implements PetSitterInterface {

	public void partecipaAnnuncio(Integer idAnnuncio, String usernamePetSitter) {
		EntityManager em =JPAUtil.getInstance().getEmf().createEntityManager();
		
		PetSitter petSitter= new PetSitter();
		Annuncio annuncio=new Annuncio();
		
		petSitter=em.find(PetSitter.class, usernamePetSitter);
		
		annuncio=em.find(Annuncio.class, idAnnuncio);
		
		List<PetSitter> listPetSitter=new ArrayList<PetSitter>();
		
		listPetSitter= annuncio.getPetSittersAnnuncio();
		listPetSitter.add(petSitter);
		
		annuncio.setPetSittersAnnuncio(listPetSitter);
		
		em.getTransaction().begin();
		em.persist(annuncio);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Annuncio> listaAnnunciPetSitter(){
		EntityManager em =JPAUtil.getInstance().getEmf().createEntityManager();
		
		List<Annuncio> _return= new ArrayList<Annuncio>();
		
		_return=em.createNamedQuery("annuncio.findAll").getResultList();
		return _return;
	}
	/*
	public List<Annuncio> preferitiPetSitter(String username){
		List<Annuncio> _return = new ArrayList<Annuncio>();
		EntityManager em =JPAUtil.getInstance().getEmf().createEntityManager();
		
		_return = em.createNamedQuery("annuncio.findByPetSitter").getResultList();
		return _return;
	}
	
    */
	
	public void creaPreferito(String usernamePetSitter, Integer idAnnuncio) {
		EntityManager em =JPAUtil.getInstance().getEmf().createEntityManager();
		Preferiti preferito = new Preferiti();
		PetSitter petSitter = new PetSitter();
		Annuncio annuncio = new Annuncio();
		
		petSitter = em.find(PetSitter.class, usernamePetSitter);
		annuncio = em.find(Annuncio.class, idAnnuncio);
		
		preferito.setPreferitoDelPetSitter(petSitter);
		preferito.setAnnuncioPreferito(annuncio);
		em.getTransaction().begin();
		em.persist(preferito);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Preferiti> visualizzaPreferitiPetSitter(String usernamePetSitter) {
		EntityManager em =JPAUtil.getInstance().getEmf().createEntityManager();
		List<Preferiti> _return = new ArrayList<Preferiti>();
		_return = em.createNamedQuery("preferiti.findByPetSitter").setParameter("name", usernamePetSitter).getResultList();
		return _return;
	}
	
	
	
	}


