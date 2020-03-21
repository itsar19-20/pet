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

import business.ProprietarioManager;
import business.UtenteAppManager;
import model.Preferiti;

/**
 * Servlet implementation class PreferitiProprietarioController
 */
@WebServlet("/PreferitiProprietarioController")
public class PreferitiProprietarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(UtenteAppController.class);   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferitiProprietarioController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProprietarioManager proprietarioManager = new ProprietarioManager();
		String usernameProprietario = request.getParameter("usernameProprietario");
		ObjectMapper om = new ObjectMapper(); 
		List<Preferiti> listaPreferiti = new ArrayList<Preferiti>();
		
		log.debug("doGet listaPreferitiProprietario, PreferitiProprietarioController Pronto");
		
		listaPreferiti = proprietarioManager.visualizzaPreferitiProprietario(usernameProprietario);
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(listaPreferiti));
		log.debug("doGet listaPreferitiProprietario, PreferitiProprietarioController Funziona");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProprietarioManager proprietarioManager = new ProprietarioManager();
		String usernameProprietario = request.getParameter("usernameProprietario");
		String usernamePetSitter = request.getParameter("usernamePetSitter");
		String idAnnuncioStringa = request.getParameter("idAnnuncioStringa");
		Integer idAnnuncio = Integer.valueOf(idAnnuncioStringa);
		log.debug("doPost creaPreferitoProprietario, PreferitiProprietarioController Pronto");
		proprietarioManager.creaPreferitoProprietario(usernameProprietario, idAnnuncio, usernamePetSitter);
		log.debug("doGet creaPreferitoProprietario, PreferitiProprietarioController Funziona");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteAppManager utenteAppManager = new UtenteAppManager();
		String idPreferitoStringa = request.getParameter("idPreferitoStringa");
		Integer idPreferito = Integer.valueOf(idPreferitoStringa);
		log.debug("doDelete eliminaPreferitoProprietario, PreferitiProprietarioController Pronto");
		utenteAppManager.eliminaPreferito(idPreferito);
		log.debug("doDelete eliminaPreferitoProprietario, PreferitiProprietarioController Funziona");
	}

}
