package it.pokeronline.web.servlet.play;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.pokeronline.dto.UserDTO;

@WebServlet("/play/PrepareSearchPartiteServlet")
public class PrepareSearchPartiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public PrepareSearchPartiteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("userAttribute", new UserDTO());
		request.getRequestDispatcher("/play/searchPartite.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
