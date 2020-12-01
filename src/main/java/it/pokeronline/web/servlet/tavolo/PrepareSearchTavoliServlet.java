package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.model.user.User;
import it.pokeronline.service.user.UserService;

@WebServlet("/tavolo/PrepareSearchTavoliServlet")
public class PrepareSearchTavoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public PrepareSearchTavoliServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUtenteCreatore = request.getParameter("idUser");
		
		//parametro da passare alla jsp per effettuare la ricerca dei tavoli creati
		User userDaDb = userService.caricaSingoloUser(Long.parseLong(idUtenteCreatore));

		request.setAttribute("utentePerSearchTavoli", userDaDb);
		
		request.getRequestDispatcher("/tavolo/searchTavoli.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
