package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Jogo;
import persistence.JogoDao;

@WebServlet("/pesquisa")
public class PesquisaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JogoDao jDao;
	
    public PesquisaController() {
        super();
        jDao = new JogoDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("pesquisa.jsp");
		request.setAttribute("jogos", new ArrayList<Jogo>());
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("pesquisa.jsp");
		String date = request.getParameter("pesquisa");
		try {
			request.setAttribute("jogos", jDao.pesquisarJogo(date));
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
