package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.persistence.EntityManager;

import model.Immagine;
import model.PetSitter;
import utils.JPAUtil;

public class TestImmagini {

	public static void main(String[] args) throws IOException {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Immagine immagineCiccio = new Immagine();
		PetSitter ps = new PetSitter();

		ps.setUsername("utentemedio");
		ps.setPassword("password");
		ps.setTipoUtente("petsitter");
		
		//File fileImmagine = new File("C:/Users/ProICT01/Desktop/logoapppet6.png");
		File fileImmagine = new File("C:/Users/ProICT01/Desktop/ciccio.png");
		byte[] byteImmagine = new byte[(int) fileImmagine.length()];
		FileInputStream fis = new FileInputStream(fileImmagine);
		fis.read(byteImmagine);
		immagineCiccio.setByteArray(byteImmagine);
		ps.setImmagineProfilo(immagineCiccio);
		fis.close();
		
		em.getTransaction().begin();
		em.persist(immagineCiccio);
		em.persist(ps);
		em.getTransaction().commit();
		
		
		
		
		

	}

}
