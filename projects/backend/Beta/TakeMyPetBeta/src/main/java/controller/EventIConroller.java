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
@WebServlet("/EventIConroller")
public class EventIConroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ImmagineController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventIConroller() {
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
	response.getWriter().write(objectMapper.writeValueAsString(listaEventi));
	log.debug("\"doGet nuovo listaEventi funziona");
	
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Evento evento=new Evento();
		UtenteAppManager utenteAppManager=new UtenteAppManager();
		String nomeEvento=request.getParameter("nomeEvento");
		Date dataEvento=new Date();
		String descrizione=request.getParameter("descrizione");
		String latitudine=null;
		String longitudine=null;
		String organizzatore=request.getParameter("usernameOrgnizzatore");
		utenteAppManager.nuovoEvento(nomeEvento, dataEvento, descrizione, latitudine, longitudine, organizzatore);
		log.debug("doPost nuovo Evento funziona");
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
