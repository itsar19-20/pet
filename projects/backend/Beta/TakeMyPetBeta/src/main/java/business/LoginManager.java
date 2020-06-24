package business;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.LoginController;
import interfaces.LoginInterface;
import model.Email;
import model.Utente;
import model.UtenteApp;
import utils.JPAUtil;

public class LoginManager implements LoginInterface {
	private static Logger log = LoggerFactory.getLogger(LoginManager.class);
	
	public Utente login(String username, String password) throws AddressException, MessagingException {

		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente u = em.find(Utente.class, username);
		
		log.debug("LoginManager Pronto");

		if (u != null) {
			u.setDataOraUltimoLogin(new Date());
			em.getTransaction().begin();
			//em.remove(em.find(Utente.class, username));
			em.persist(u);
			em.getTransaction().commit();
			

			if (u.getPassword().contentEquals(password)) {
				if(!u.isBloccato()) {
				u.setContatoreAccessiSbagliati(0);
				//((UtenteApp) u).setAttivo(true);
				em.getTransaction().begin();
				//em.remove(em.find(Utente.class, username));
				em.persist(u);
				em.getTransaction().commit();
				log.debug("LoginManager Funziona");
				log.debug("Utente trovato");
				em.close();
				return u;
				}
				else {
					MailManager mailManager= new MailManager();
					String codiceSblocco = mailManager.generateUnlockCode();
					for(Email email: u.getEmails()) {
						mailManager.sendMail(mailManager.getUSERNAME(), mailManager.getPASSWORD(), email.getEmail(), "Codice sblocco account Take My Pet App", "Clicca su questo link: http://localhost:8080/sblocco.html ed inserisci il tuo username e il seguente codice: " + codiceSblocco);
					}
					u.setCodiceSblocco(codiceSblocco);
					em.getTransaction().begin();
					em.persist(u);
					em.getTransaction().commit();
					em.close();
					return u;
					
					}
			} 
			
			else{
				Integer i = u.getContatoreAccessiSbagliati();
				i++;
				u.setContatoreAccessiSbagliati(i);
				em.getTransaction().begin();
				//em.remove(em.find(Utente.class, username));
				em.persist(u);
				em.getTransaction().commit();
				
					if(u.getContatoreAccessiSbagliati() == 10) {
						MailManager mailManager= new MailManager();
						String codiceSblocco = mailManager.generateUnlockCode();
						u.setCodiceSblocco(codiceSblocco);
						for(Email email: u.getEmails()) {
							mailManager.sendMail(mailManager.getUSERNAME(), mailManager.getPASSWORD(), email.getEmail(), "Codice sblocco account Take My Pet App", "Clicca su questo link: http://localhost:8080/sblocco.html ed inserisci il tuo username e il seguente codice: " + codiceSblocco);
						}
					em.getTransaction().begin();
					//em.remove(u);
					u.setBloccato(true);
					em.persist(u);
					em.getTransaction().commit();
					em.close();
					return u;
					}
				

			}
			if(u.isBloccato()) {
				em.close();
				return u;
			}
			else {
				em.close();
				return null;
			}
		}
		log.debug("LoginManager Funziona");
		log.debug("Utente NON trovato");
		return null;

	}

}
