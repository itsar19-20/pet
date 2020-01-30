package test;

import javax.persistence.EntityManager;

import model.Admin;
import model.PetSitter;
import utils.JPAUtil;

public class TestDB {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		PetSitter ps = new PetSitter();

		ps.setUsername("giorgio");
		ps.setPassword("password");
		ps.setBloccato(false);
		ps.setAttivo(true);

		Admin admin = new Admin();
		
		admin.setUsername("alex");
		admin.setPassword("password");
		admin.setTipoUtente("admin");

		em.getTransaction().begin();
		em.persist(admin);
		em.getTransaction().commit();
	}

}
