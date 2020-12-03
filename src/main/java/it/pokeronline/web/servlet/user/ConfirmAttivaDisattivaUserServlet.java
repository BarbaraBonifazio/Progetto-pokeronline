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

@WebServlet("/user/ConfirmAttivaDisattivaUserServlet")
public class ConfirmAttivaDisattivaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	   UserService userService;
	   
	   @Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		}
	
    public ConfirmAttivaDisattivaUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idDaInviareAExecuteDisattiva");
		
		User userDaDb = userService.caricaSingoloUser(Long.parseLong(idUser));
		
		if(userDaDb.getStato() == StatoUser.NON_ATTIVO) {
			userDaDb.setStato(StatoUser.ATTIVO);
			userService.aggiorna(userDaDb);
			request.setAttribute("successMessage", "L'utente è stato riattivato!");
			List<User> listaUtenti = userService.listAllUsersWithRuoli();
			request.setAttribute("usersPerResults", listaUtenti);
			request.getRequestDispatcher("/user/results.jsp").forward(request, response);
			return;
		} else {
		Boolean isInGioco = false;
		if(userDaDb.getTavolo() != null) {
			isInGioco = true;
		}
		request.setAttribute("isInGioco", isInGioco);
		request.setAttribute("userAttribute", userDaDb);
		request.getRequestDispatcher("/user/confirmDisattiva.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
