package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.pokeronline.dto.TavoloDTO;

@WebServlet("/PrepareInsertTavoloServlet")
public class PrepareInsertTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareInsertTavoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idUtenteCreatore = request.getParameter("idUser");

		request.setAttribute("idUserPerInsertTavolo", idUtenteCreatore);
		request.setAttribute("tavoloAttribute", new TavoloDTO());
		request.getRequestDispatcher("/tavolo/insert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
