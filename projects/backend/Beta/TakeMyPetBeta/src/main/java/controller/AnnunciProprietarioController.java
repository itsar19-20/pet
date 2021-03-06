package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.Annuncio;

/**
 * Servlet implementation class AnnuncioController
 */
@WebServlet("/AnnunciProprietarioController")
public class AnnunciProprietarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ImmagineController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnunciProprietarioController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProprietarioManager proprietarioManager = new ProprietarioManager();
		ObjectMapper om = new ObjectMapper();
		List<Annuncio> listaAnnunciProprietario = new ArrayList<Annuncio>();
		
		String usernameProprietario = request.getParameter("usernameProprietario");
		
		listaAnnunciProprietario = proprietarioManager.listaAnnunciProprietario(usernameProprietario);
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(listaAnnunciProprietario));
		log.debug("doGet lista Annunci Proprietario funziona");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProprietarioManager proprietarioManager = new ProprietarioManager();
		String idAnnuncioString = request.getParameter("idAnnuncioString");
		
		Integer idAnnuncio = Integer.valueOf(idAnnuncioString);
		proprietarioManager.rimuoviAnnuncio(idAnnuncio);
		log.debug("doDelete elimina Annuncio Proprietario funziona");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProprietarioManager proprietarioManager=new ProprietarioManager();
		String usernameProprietario=request.getParameter("usernameProprietario");
		String descrizioneProprietario=request.getParameter("descrizioneProprietario");
		String nomeAnnuncio = request.getParameter("nomeAnnuncio");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAnnuncio = null;
		log.debug("Data annuncio"+request.getParameter("dataAnnuncio"));
		
		try {
			dataAnnuncio = formatter.parse(request.getParameter("dataAnnuncio"));
			dataAnnuncio.setDate(dataAnnuncio.getDate()+1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String urlImmagineAnnuncio =request.getParameter("urlImmagineAnnuncio");
		
		//da sistemare dopo
		String latitudine=null;
		String longitudine=null;
	
		Date dataCreazioneAnnuncio = null;
		//Date dataAnnuncio = null;
		
		
		List<Animale> listaAnimali=new ArrayList<Animale>();
		//prendere dal body la lista animali e convertirla dal Json
		proprietarioManager.aggiungiAnnuncio(nomeAnnuncio, usernameProprietario, descrizioneProprietario, longitudine, latitudine, listaAnimali, dataAnnuncio, dataCreazioneAnnuncio,urlImmagineAnnuncio);
		
		
	}

}
