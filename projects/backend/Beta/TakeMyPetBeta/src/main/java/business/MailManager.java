package business;

import interfaces.MailInterface;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailManager implements MailInterface{
	
	private final String USERNAME = "takemypetapp@gmail.com";
	private final String PASSWORD = "P4ssw0rdID";

	@Override
	public void sendMail(final String username,final String password, String toAddress, String subject,
			String message) throws AddressException, MessagingException {
		
		//set proprietà SMTP
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		//creo nuova sessione con l'authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};
		
		Session session = Session.getInstance(properties, auth);
		 
		// creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(username));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
       
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setText(message);
 
        // sends the e-mail
        Transport.send(msg);
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}
	
	public String generateUnlockCode() {
		Random random = new Random();
		String _return = "TMP-";
		for(int i = 0; i <= 4 ; i++) {
			
			_return = _return + Integer.toString(random.nextInt(10));
		}
	
		return _return;
	}
	
	

}