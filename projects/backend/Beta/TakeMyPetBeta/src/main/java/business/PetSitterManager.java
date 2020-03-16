
package business;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.PetSitterInterface;
import model.Annuncio;
import model.PetSitter;
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
	

	}


