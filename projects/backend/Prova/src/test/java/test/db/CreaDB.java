package test.db;

import javax.persistence.EntityManager;

import utils.JPAUtil;

public class CreaDB {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
	}

}
