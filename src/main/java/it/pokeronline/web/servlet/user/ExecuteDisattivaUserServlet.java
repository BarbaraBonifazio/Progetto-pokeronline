package it.pokeronline.web.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;
import it.pokeronline.service.user.UserService;


@WebServlet("/user/ExecuteDisattivaUserServlet")
public class ExecuteDisattivaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ExecuteDisattivaUserServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUserInput = request.getParameter("id");
		User userDaDb = userService.caricaSingoloUser(Long.parseLong(idUserInput));
		if(userDaDb.getTavolo() != null) {
			userDaDb.setTatolo(null);
		}
		
		userDaDb.setStato(StatoUser.NON_ATTIVO);
		userService.aggiorna(userDaDb);
		
		request.setAttribute("successMessage", "L'utente è stato disattivato!");
		List<User> listaUtenti = userService.listAllUsersWithRuoli();
		request.setAttribute("usersPerResults", listaUtenti);

		// andiamo ai risultati
		request.getRequestDispatcher("/user/results.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
