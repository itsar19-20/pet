package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import org.apache.commons.codec.*;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import business.UtenteAppManager;
import model.UtenteApp;

/**
 * Servlet implementation class ImmagineController
 */
@WebServlet("/getImmagine")
public class ImmagineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ImmagineController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImmagineController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteAppManager uam = new UtenteAppManager() {};
		UtenteApp utente = new UtenteApp();
		
		String username = request.getParameter("username");
		utente = uam.visualizzaProfilo(username);
		String urlImmagine = null;
		if(utente != null) {
		urlImmagine = utente.getImmagineProfilo().getUrlImmagine();
		}
		
		log.debug("ImmagineController Pronto");
		
		//DA MODIFICARE LA RISPOSTA PER JAVASCRIPT
		
		
		response.getWriter().append(urlImmagine);
		
		log.debug("ImmagineController Funziona");
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UtenteAppManager uam = new UtenteAppManager() {};
		ObjectMapper om = new ObjectMapper();
		
		log.debug("ImmagineController Pronto");
		
		
		
		String username = request.getParameter("username");
		//request.setCharacterEncoding("UTF-8");
		
		String immagine = request.getParameter("immagine");
		
		byte[] byteImmagine;
	
		if(immagine != null) {
			//Mi arriva il base64 da jquery
			byteImmagine = java.util.Base64.getDecoder().decode(immagine);
		} else {
			//per prendere il body da android
			BufferedReader br = request.getReader();
			
			log.debug(br.readLine());
			log.debug(br.readLine());
			log.debug(br.readLine());
			log.debug(br.readLine());
			log.debug(br.readLine());
			
			StringBuilder builder = new StringBuilder();
			String s;
			while((s = br.readLine()) != null) {
			builder.append(s);
		}
			String base64Letto = builder.toString();
		    log.debug(base64Letto);
			String base64Cut = base64Letto.substring(0, base64Letto.length() - 44);
			log.debug(base64Cut);
			byteImmagine = java.util.Base64.getDecoder().decode(base64Cut);
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(byteImmagine);
		BufferedImage image = ImageIO.read(bais);
		OutputStream os = new FileOutputStream(new File("src/main/webapp/immaginiPerWeb/" + username +".jpg"));
		ImageIO.write(image, "jpg", os);
		os.close();
		
		//mettere sito app o localhost o ip computer server
		String urlImmagine = "http://192.168.1.103:8080/immaginiPerWeb/" + username +".jpg";
		uam.inserisciImmagine(username, urlImmagine);
		String controllo = "Immagine salvata con successo";
		response.setContentType("application/json");
	    response.getWriter().append(om.writeValueAsString(controllo));
		
		log.debug("ImmagineController Funziona");
	}

}
