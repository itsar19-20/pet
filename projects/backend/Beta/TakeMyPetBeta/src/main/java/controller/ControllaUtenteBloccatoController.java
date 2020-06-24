package controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.ControlloBloccatoManager;
import business.LoginManager;
import model.Utente;
import utils.JPAUtil;

/**
 * Servlet implementation class ControllaUtenteBloccatoController
 */
@WebServlet("/ControllaUtenteBloccatoController")
public class ControllaUtenteBloccatoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ControllaUtenteBloccatoController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllaUtenteBloccatoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		ControlloBloccatoManager cbManager = new ControlloBloccatoManager();
		
		String controllo;
		log.debug("ControlloUtenteBloccatoController pronto");
	
		String username = request.getParameter("username");
		Utente utente = cbManager.controlloBlocco(username);
	
		if(utente.isBloccato()) {
			controllo = "bloccato";
		} else if(!utente.isAttivo()) {
			controllo = "disattivato";
		} else {
			controllo = "ok";
			
		}
		
		response.getWriter().append(controllo);
		
		
		log.debug("ControlloUtenteBloccatoController funziona");
		
		
		}

	

}
