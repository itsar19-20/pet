package test;

import javax.persistence.EntityManager;

import model.Admin;
import model.Proprietario;
import utils.JPAUtil;

public class TestDB {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		Admin a = new Admin();
		Proprietario ps = new Proprietario();
		
		ps.setUsername("alex");
		ps.setPassword("password");
		ps.setTipoUtente("proprietario");
		ps.setBloccato(false);
		ps.setAttivo(true);
		a.setUsername("giorgio");
		a.setPassword("password");
		a.setTipoUtente("admin");
		
		
	
		
		em.getTransaction().begin();
		em.persist(ps);
		em.persist(a);
		em.getTransaction().commit();
	}

}
