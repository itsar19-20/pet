package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import model.UtenteApp;

/**
 * Servlet implementation class EventIConroller
 */
@WebServlet("/EventiController")
public class EventiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ImmagineController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Evento> listaEventi=new  ArrayList<Evento>();
		UtenteAppManager utenteAppManager=new UtenteAppManager();
		ObjectMapper objectMapper=new ObjectMapper();
		
	listaEventi=utenteAppManager.visualizzaTuttiEventi();
	response.setContentType("application/json");
	response.getWriter().append(objectMapper.writeValueAsString(listaEventi));
	log.debug("\"doGet lista tutti Eventi funziona");
	
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Evento evento=new Evento();
		ObjectMapper om = new ObjectMapper();
		UtenteAppManager utenteAppManager=new UtenteAppManager();
		String nomeEvento=request.getParameter("nomeEvento");
		Date dataEvento=new Date();
		String descrizione=request.getParameter("descrizione");
		String latitudine=null;
		String longitudine=null;
		String organizzatore=request.getParameter("usernameOrgnizzatore");
		utenteAppManager.nuovoEvento(nomeEvento, dataEvento, descrizione, latitudine, longitudine, organizzatore);
		String controllo = "Evento salvato con successo";
		response.setContentType("application/json");
	    response.getWriter().append(om.writeValueAsString(controllo));
		log.debug("doPost nuovo Evento funziona");
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteAppManager utenteAppManager = new UtenteAppManager();
		String idEventoString = request.getParameter("idEventoString");
		String usernamePartecipante = request.getParameter("usernamePartecipante");
		int idEvento = Integer.parseInt(idEventoString);
		utenteAppManager.partecipaEvento(idEvento, usernamePartecipante);
		log.debug("doPut partecipa Evento funziona");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteAppManager utenteAppManager = new UtenteAppManager();
		String idEventoString = request.getParameter("idEventoString");
		Integer idEvento = Integer.valueOf(idEventoString);
		utenteAppManager.eliminaEvento(idEvento);
		log.debug("doDelete elimina Evento Funziona");
		
	}

}
