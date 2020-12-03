package it.pokeronline.web.servlet.play;

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
import it.pokeronline.util.Util;

@WebServlet("/play/ExecuteLasciaPartitaServlet")
public class ExecuteLasciaPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ExecuteLasciaPartitaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User userInSession = (User)request.getSession().getAttribute("user");
		
//		 il sistema fa il ++ di esperienza. Qui si individua immediatamente un bug cioè 
//		 qualcuno per accumulare esperienza potrebbe entrare e uscire n volte senza giocare. Ma a noi non importa…
		
		String creditoAcquistato = request.getParameter("credito");

		if(!Util.isInteger(creditoAcquistato)) {
			request.setAttribute("credito", creditoAcquistato);
			request.setAttribute("errorMessage", "Il campo Cifra non è stato valorizzato correttamente!");
			request.getRequestDispatcher("/play/compraCredito.jsp").forward(request, response);
			return;
		}
		
		userInSession.setCreditoAccumulato(userInSession.getCreditoAccumulato() + Integer.parseInt(creditoAcquistato));
		userService.aggiorna(userInSession);
		request.setAttribute("successMessage", "Hai acquistato con successo " + creditoAcquistato + " euro!");
		request.getRequestDispatcher("/play/compraCredito.jsp").forward(request, response);
	}

}
