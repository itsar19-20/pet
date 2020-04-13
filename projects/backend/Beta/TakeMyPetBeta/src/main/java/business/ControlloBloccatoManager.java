package business;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Utente;
import utils.JPAUtil;

public class ControlloBloccatoManager {
	private static Logger log = LoggerFactory.getLogger(ControlloBloccatoManager.class);
	
	public Utente controlloBlocco(String username) {
	log.debug("ControlloBloccatoManager pronto");
	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	Utente _return = em.find(Utente.class, username);
	em.close();
	log.debug("ControlloBloccatoManager funziona");
	return _return;
	}
	
	

}
