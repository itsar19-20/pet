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

import business.PetSitterManager;
import business.ProprietarioManager;
import business.UtenteAppManager;
import model.Animale;
import model.Utente;

/**
 * Servlet implementation class UtenteAppController
 */
@WebServlet("/UtenteAppController")
public class UtenteAppController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(UtenteAppController.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public UtenteAppController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteAppManager pm= new ProprietarioManager();
		UtenteAppManager ps= new PetSitterManager();
		ObjectMapper om = new ObjectMapper();
		
		log.debug("UteneAppController Pronto");
	
		
		
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(pm));
		response.getWriter().append(om.writeValueAsString(ps));
		
		log.debug("AdminController Funziona");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
