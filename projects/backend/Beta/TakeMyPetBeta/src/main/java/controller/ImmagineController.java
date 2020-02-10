package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		byte[] byteArray = utente.getImmagineProfilo().getByteArray();
		
		log.debug("ImmagineController Pronto");
		
		//ocho all'asterisco, per ora funziona
		response.setContentType("image/*");
		OutputStream out = response.getOutputStream();
		out.write(byteArray);
		
		log.debug("ImmagineController Funziona");
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UtenteAppManager uam = new UtenteAppManager() {};
		ObjectMapper om = new ObjectMapper();
		
		log.debug("ImmagineController Pronto");
		
		String username = request.getParameter("username");
		//request.setCharacterEncoding("UTF-8");
		
		String immagine = request.getParameter("immagine");
		
		byte[] bytearray = Base64.getDecoder().decode(immagine);
		
		uam.inserisciImmagine(username, bytearray);
		
		log.debug("ImmagineController Funziona");
	}

}