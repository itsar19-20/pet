package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.RegistrationManager;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/signUp")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(RegistrationController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationController() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RegistrationManager rm = new RegistrationManager();
		ObjectMapper om = new ObjectMapper();

		String nome = request.getParameter("name");
		String cognome = request.getParameter("surname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Date dataDiNascita = null; // = (request.getParameter("dataDiNascita");
		Date dataDiRegistrazione = new Date();
		String descrizione = null;//request.getParameter("descrizione");
		String latitudine = null; //request.getParameter("latitudine");
		String longitudine = null; //request.getParameter("longitudine");
		String tipo = request.getParameter ("type");
		boolean doppioProfilo = false; // = request.getParameter("doppioprofilo");
		
		log.debug("RegistrationController Pronto");
		
		String controllo = rm.registrazione(doppioProfilo, tipo, nome, cognome, email, username, password, dataDiNascita, dataDiRegistrazione, descrizione, latitudine, longitudine, null);
		
		response.setContentType("application/json");
	    response.getWriter().append(om.writeValueAsString(controllo));
	    //response.getWriter().append("Utente registrato senza errori");
	    
	    log.debug("RegistrationController Funziona");
	    
	    
	}

}
