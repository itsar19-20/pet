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

import business.AdminManager;

/**
 * Servlet implementation class StatAdminController
 */
@WebServlet("/statisticheAdmin")
public class StatAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(StatAdminController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatAdminController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Object> lista = new ArrayList<>();
		AdminManager am = new AdminManager();
		ObjectMapper om = new ObjectMapper();
		
		log.debug("StatAdminController Pronto");
		
		lista = am.statUtentiRegistratiDay();
		
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(lista));
		
		log.debug("StatAdminController Funziona");
		
	}

}
