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
import model.Annuncio;

/**
 * Servlet implementation class AnnunciPetSitterController
 */
@WebServlet("/AnnunciPetSitterController")
public class AnnunciPetSitterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ImmagineController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnunciPetSitterController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetSitterManager petSitterManager = new PetSitterManager();
		ObjectMapper om = new ObjectMapper();
		List<Annuncio> listaAnnunciPetSitter = new ArrayList<Annuncio>();
		
		listaAnnunciPetSitter = petSitterManager.listaAnnunciPetSitter();
		
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(listaAnnunciPetSitter));
		log.debug("doGet lista Annunci PetSitter funziona");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetSitterManager petSitterManager = new PetSitterManager();
		String usernamePetSitter = request.getParameter("usernamePetSitter");
		String idAnnuncioString = request.getParameter("idAnnuncioString");
		
		Integer idAnnuncio = Integer.valueOf(idAnnuncioString);
				
		petSitterManager.partecipaAnnuncio(idAnnuncio, usernamePetSitter);
		log.debug("doPost partecipa Annuncio PetSitter funziona");
	}

}
