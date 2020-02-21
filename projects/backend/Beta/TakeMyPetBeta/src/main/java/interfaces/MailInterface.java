package interfaces;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MailInterface {
	
	abstract void sendMail(final String username, final String password, String toAddress, String subject, String message) throws AddressException, MessagingException;

}
