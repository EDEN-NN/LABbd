package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.JogoDao;

@WebServlet("/jogos")
public class JogoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JogoDao jDao;

	public JogoController() {
		super();
		this.jDao = new JogoDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("jogos.jsp");
		try {
			request.setAttribute("jogos", jDao.gerarJogos());
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("jogos.jsp");
		try {
			this.jDao.randomizarJogos();
			request.setAttribute("jogos", jDao.gerarJogos());
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
