package test;

import business.MailManager;

public class testEmail {
	public static void main(String[] args) {
        // SMTP server information
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "takemypetapp@gmail.com";
        String password = "P4ssw0rdID";
 
        // outgoing message information
        String mailTo = "a.dicarmine@itsrizzoli.it";
        String subject = "Daje";
        String message = "Sono Ciccio, sempre io.";
 
        MailManager mailer = new MailManager();
        System.out.println(mailer.generateUnlockCode() + " agayufdiyas");
 
        /*try {
            mailer.sendMail(host, port, mailFrom, password, mailTo, subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }*/
}
}

