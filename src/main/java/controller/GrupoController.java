package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.GenericDao;
import persistence.GrupoDao;
import persistence.TimeDao;

@WebServlet("/grupos")
public class GrupoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       GrupoDao grDao;
       GenericDao gDao;
       TimeDao tDao;
 
    public GrupoController() {
        super();
        this.gDao = new GenericDao();
        this.tDao = new TimeDao(this.gDao);
        this.grDao = new GrupoDao(this.gDao, this.tDao);
   
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		try {
			request.setAttribute("grupos", grDao.gerarGrupos());
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		try {
			this.grDao.randomizarGrupos();
			request.setAttribute("grupos", grDao.gerarGrupos());
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
