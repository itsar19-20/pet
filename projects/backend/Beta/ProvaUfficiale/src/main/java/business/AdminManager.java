package business;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.AdminInterface;
import model.Segnalazione;
import utils.JPAUtil;

public class AdminManager implements AdminInterface {

	public List<Segnalazione> visualizzaSegnalazioni() {
		
		List<Segnalazione> _return = new ArrayList();
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		for(Segnalazione s : em.createQuery("select c from segnalazione c", Segnalazione.class).getResultList()) {
			_return.add(s);
		}
			
		return _return;
	}

}
