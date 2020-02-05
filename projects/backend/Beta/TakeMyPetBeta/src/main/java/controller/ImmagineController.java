package controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.UtenteAppManager;
import model.Evento;
import model.UtenteApp;

/**
 * Servlet implementation class ImmagineController
 */
@WebServlet("/getImmagine")
public class ImmagineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImmagineController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteAppManager uam = new UtenteAppManager() {};
		UtenteApp utente = new UtenteApp();

		
		String username = request.getParameter("username");
		utente = uam.visualizzaProfilo(username);
		byte[] byteArray = utente.getImmagineProfilo().getByteArray();
		
		response.setContentType("image/png");
		OutputStream out = response.getOutputStream();
		out.write(byteArray);
		
			
				
		
		
	}

}
