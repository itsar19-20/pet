package test;

import javax.persistence.EntityManager;

import model.PetSitter;
import utils.JPAUtil;

public class TestDB {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		PetSitter ps = new PetSitter();
		
		ps.setUsername("Giorgio");
		ps.setPassword("password");
		ps.setBloccato(true);
		
	
		
		em.getTransaction().begin();
		em.refresh(ps);
		em.getTransaction().commit();
	}

}
