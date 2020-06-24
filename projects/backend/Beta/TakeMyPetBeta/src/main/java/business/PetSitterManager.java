
package business;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		em.close();
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
	
	public String creaPreferito(String usernamePetSitter, int idAnnuncio) {
		
		EntityManager em =JPAUtil.getInstance().getEmf().createEntityManager();
		Preferiti preferito = new Preferiti();
		PetSitter petSitter = new PetSitter();
		Annuncio annuncio = new Annuncio();
		List <Annuncio> listAnnuncio= em.createNamedQuery("preferiti.controllaCheEventoPetSitterEsista").setParameter("name",usernamePetSitter).setParameter("id",idAnnuncio).getResultList();
		
		System.out.println("Prova petsitterManager "+listAnnuncio.size());
	if(listAnnuncio.size()<1) {
		
		//petSitter =(PetSitter) em.createNamedQuery("petsitter.findByPetSitter").setParameter("name",usernamePetSitter).getSingleResult();
		annuncio = em.find(Annuncio.class, idAnnuncio);
		petSitter=(PetSitter) em.getReference(PetSitter.class,usernamePetSitter);
		
		preferito.setPreferitoDelPetSitter(petSitter);
		preferito.setAnnuncioPreferito(annuncio);
		System.out.println("Annuncio");
		System.out.println("Diego"+annuncio);
		System.out.println("Tsipas"+annuncio.getProprietarioAnnuncio());
		preferito.setPreferitoDelProprietario(annuncio.getProprietarioAnnuncio());
		em.getTransaction().begin();
		em.persist(preferito);
		em.getTransaction().commit();
		em.close();
		
		return "OK";
	}
	
	else
		return null;
	}
	
	public List<Preferiti> visualizzaPreferitiPetSitter(String usernamePetSitter) {
		EntityManager em =JPAUtil.getInstance().getEmf().createEntityManager();
		List<Preferiti> _return = new ArrayList<Preferiti>();
		_return = em.createNamedQuery("preferiti.findByPetSitter").setParameter("name", usernamePetSitter).getResultList();
		em.close();
		return _return;
	}

	
	
	public List<PetSitter> visualizzaPetSitterPerAnnuncio(int id_annuncio) {
		EntityManager em =JPAUtil.getInstance().getEmf().createEntityManager();
		List<PetSitter> _return = new ArrayList<PetSitter>();
		_return = em.createNamedQuery("petsitter.findByPreferiti").setParameter("id", id_annuncio).getResultList();
		em.close();
		return _return;
	}
	
	}


