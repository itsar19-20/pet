package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.ProprietarioManager;
import model.Animale;

/**
 * Servlet implementation class AnnuncioController
 */
@WebServlet("/AnnuncioController")
public class AnnuncioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnuncioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProprietarioManager proprietarioManager=new ProprietarioManager();
		String usernameProprietario=request.getParameter("usernameProprietario");
		String descrizioneProprietario=request.getParameter("descrizioneProprietario");
		
		//da sistemare dopo
		String latitudine=null;
		String longitudine=null;
		
		List<Animale> listaAnimali=new ArrayList<Animale>();
		//prendere dal body la lista animali e convertirla dal Json
		
		
	}

}
