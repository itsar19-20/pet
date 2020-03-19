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

import business.UtenteAppManager;
import model.Evento;

/**
 * Servlet implementation class EventiUtenteController
 */
@WebServlet("/EventiUtenteController")
public class EventiUtenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ImmagineController.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventiUtenteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Evento> listaEventi=new  ArrayList<Evento>();
		UtenteAppManager utenteAppManager=new UtenteAppManager() ;
		String username=request.getParameter("username");
		ObjectMapper objectMapper= new ObjectMapper();
		
		
	listaEventi=utenteAppManager.visualizzaEventiUtente(username);
	response.setContentType("aplication/json");
	
	response.getWriter().append(objectMapper.writeValueAsString(listaEventi));
	log.debug("EventiUtenteController funziona.");
	
	}

}
