package test;

import javax.persistence.EntityManager;

import model.Utente;
import utils.JPAUtil;

public class TestRefreshDB {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		
		em.getTransaction().begin();
		em.refresh(em.find(Utente.class, "Giorgio"));
		em.getTransaction().commit();
		
	}

}
