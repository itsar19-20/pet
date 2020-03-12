package controller;

import java.awt.image.BufferedImage;
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

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.security.ntlm.Server;

import business.UtenteAppManager;
import model.Evento;
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
	
	
		//Mi arriva il base64 dal javascript
		byte[] byteImmagine;
		
		if(Base64.isBase64(immagine)) {
	    	byteImmagine = java.util.Base64.getDecoder().decode(immagine);
	    } else {
	    	byteImmagine = immagine.getBytes();
	    }
		
		
		ByteArrayInputStream bais = new ByteArrayInputStream(byteImmagine);
		BufferedImage image = ImageIO.read(bais);
		OutputStream os = new FileOutputStream(new File("src/main/webapp/immaginiPerWeb/" + username +".jpg"));
		ImageIO.write(image, "jpg", os);
		os.close();
		
		//mettere sito app o localhost o ip computer server
		String urlImmagine = "http://192.168.1.103:8080/immaginiPerWeb/" + username +".jpg";
		
		uam.inserisciImmagine(username, urlImmagine);
		
		log.debug("ImmagineController Funziona");
	}

}
