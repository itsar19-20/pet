package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.AuthenticationManager;
import model.Admin;
import model.PetSitter;
import model.Proprietario;
import model.Utente;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("paginaLogin.html").forward(request, response);

		AuthenticationManager am = new AuthenticationManager();
		Utente u = am.login(request.getParameter("username"), request.getParameter("password"));
		
		if(u instanceof Admin) {
			request.getRequestDispatcher("/admin.html").forward(request,response);
		}
		else if (u instanceof PetSitter || u instanceof Proprietario) {
			request.getRequestDispatcher("/ok.html").forward(request,response);
		}
		else {
			request.getRequestDispatcher("/no.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
