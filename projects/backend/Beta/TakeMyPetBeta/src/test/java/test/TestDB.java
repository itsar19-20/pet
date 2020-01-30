package test;

import javax.persistence.EntityManager;

import model.Admin;
import model.PetSitter;
import utils.JPAUtil;

public class TestDB {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		Admin a = new Admin();
		PetSitter ps = new PetSitter();
		
		ps.setUsername("alex");
		ps.setPassword("password");
		ps.setTipoUtente("Petsitter");
		ps.setBloccato(false);
		ps.setAttivo(true);
		a.setUsername("giorgio");
		a.setPassword("password");
		a.setTipoUtente("Admin");
		
	
		
		em.getTransaction().begin();
		em.persist(ps);
		em.persist(a);
		em.getTransaction().commit();
	}

}
