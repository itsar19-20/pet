package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.AdminManager;
import model.UtenteApp;

/**
 * Servlet implementation class SbloccoController
 */
@WebServlet("/SbloccoController")
public class SbloccoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(LoginController.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SbloccoController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("username");
		String codiceSblocco = request.getParameter("codiceSblocco");
		ObjectMapper om = new ObjectMapper();
		AdminManager am = new AdminManager();
		UtenteApp u = new UtenteApp();
		log.debug("SbloccController Pronto");
		
		boolean controlloSblocco = am.sbloccoUtente(username, codiceSblocco);
		
		if (controlloSblocco) {
		u = am.cercaUtenteSingolo(username);
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(u));
		}
		
		
		log.debug("SbloccoController Funziona");
		log.debug(u.getUsername() + "e stato sbloccato e si e loggato");
		
	}

}
