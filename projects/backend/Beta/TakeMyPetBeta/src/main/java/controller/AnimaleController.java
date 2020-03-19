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

import business.ProprietarioManager;
import model.Animale;

/**
 * Servlet implementation class AnimaleController
 */
@WebServlet("/AnimaleController")
public class AnimaleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ImmagineController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimaleController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Animale> listaAnimaliProprietario = new ArrayList<Animale>();
		ObjectMapper om = new ObjectMapper();
		ProprietarioManager proprietarioManager = new ProprietarioManager();
		String usernameProprietario = request.getParameter("usernameProprietario");
		
		listaAnimaliProprietario = proprietarioManager.visualizzaAnimali(usernameProprietario);
		
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(listaAnimaliProprietario));
		log.debug("doGet lista Anumali Proprietario funziona");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProprietarioManager proprietarioManager = new ProprietarioManager();
		
		String usernameProprietario = request.getParameter("usernameProprietario");
		Date dataDiNascita = new Date(); //DA SISTEMARE
		String dettagli = request.getParameter("dettagli");
		String etaString = request.getParameter("etaString");
		int eta = Integer.parseInt(etaString);
		String nome = request.getParameter("nome");
		String razza = request.getParameter("razza");
		String tipo = request.getParameter("tipo");
		
		proprietarioManager.aggiungiAnimale(usernameProprietario, dataDiNascita, dettagli, eta, nome, razza, tipo);
		log.debug("doPost aggiungi Animale funziona");
		
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProprietarioManager proprietarioManager = new ProprietarioManager();
		String idAnimaleString = request.getParameter("idAnimaleString");
		
		Integer idAnimale = Integer.valueOf(idAnimaleString);
		
		proprietarioManager.eliminaAnimale(idAnimale);
		log.debug("doDelete elimina Animale funziona");
	}

}
