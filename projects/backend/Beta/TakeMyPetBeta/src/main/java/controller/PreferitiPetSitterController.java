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
import business.UtenteAppManager;
import model.Preferiti;

/**
 * Servlet implementation class PreferitiPetSitterController
 */
@WebServlet("/PreferitiPetSitterController")
public class PreferitiPetSitterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(UtenteAppController.class); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferitiPetSitterController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetSitterManager petSitterManager = new PetSitterManager();
		ObjectMapper om = new ObjectMapper(); 
		List<Preferiti> listaPreferiti = new ArrayList<Preferiti>();
		
		String usernamePetSitter = request.getParameter("usernamePetSitter");
		
		listaPreferiti = petSitterManager.visualizzaPreferitiPetSitter(usernamePetSitter);
		log.debug("doGet listaPreferitiPetSitter, PreferitiPetSitterController Pronto");
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(listaPreferiti));
		log.debug("doGet listaPreferitiPetSitter, PreferitiPetSitterController Funziona");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetSitterManager petSitterManager = new PetSitterManager();
		
		String usernamePetSitter = request.getParameter("usernamePetSitter");
		String idAnnuncioString = request.getParameter("idAnnuncioString");
		Integer idAnnuncio = Integer.valueOf(idAnnuncioString);
		log.debug("doPost creaPreferitoPetSitter, PreferitiPetSitterController Pronto");
		petSitterManager.creaPreferito(usernamePetSitter, idAnnuncio);
		log.debug("doPost creaPreferitoPetSitter, PreferitiPetSitterController Funziona");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteAppManager utenteAppManager = new UtenteAppManager();
		String idPreferitoStringa = request.getParameter("idPreferitoStringa");
		Integer idPreferito = Integer.valueOf(idPreferitoStringa);
		log.debug("doDelete eliminaPreferitoPetSitter, PreferitiPetSitterController Pronto");
		utenteAppManager.eliminaPreferito(idPreferito);
		log.debug("doDelete eliminaPreferitoPetSitter, PreferitiPetSitterController Funziona");
	}

}
