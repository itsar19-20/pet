
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
	
		
		em.getTransaction().begin();
		em.persist(ps);
		em.getTransaction().commit();
	}

}
>>>>>>> master
