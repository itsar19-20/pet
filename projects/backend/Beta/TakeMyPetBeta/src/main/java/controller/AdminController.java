package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.AdminManager;
import model.Utente;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/listaUtentiAdmin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(AdminController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminManager am = new AdminManager();
		List<Utente> listaUtenti = new ArrayList<Utente>();
		ObjectMapper om = new ObjectMapper();
		listaUtenti = am.visualizzaUtenti();
	
		log.debug("AdminController Pronto");
	
		
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(listaUtenti));
		
		log.debug("AdminController Funziona");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		AdminManager am = new AdminManager();
		String controllo = request.getParameter("controllo");
		String username = request.getParameter("username");
		
		log.debug("AdminController Pronto");
		
		switch (controllo) {
		
		case "blocca":
			am.bloccaUtente(username);
			break;
			
		case "attiva":
			am.sbloccoUtente(username, null);
			break;
			
		case "elimina":
			am.eliminaUtente(username);
			break;
			
		default:
			break;
		}
		
		log.debug("AdminController Funziona");

	}

}
