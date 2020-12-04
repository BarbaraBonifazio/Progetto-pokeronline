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

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;
import it.pokeronline.service.tavolo.TavoloService;
import it.pokeronline.service.user.UserService;

@WebServlet("/play/PreparePartitaServlet")
public class PreparePartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	TavoloService tavoloService;
	
	@Autowired
	UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
    public PreparePartitaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String partita = request.getParameter("idParamTavoloPerGioca");
		User userInSession = (User)request.getSession().getAttribute("user");
		if(partita != null) {
		Tavolo tavoloDaDb = tavoloService.caricaSingoloTavolo(Long.parseLong(partita));
		if(userInSession.getTavolo() == null) {
			userInSession.setTatolo(tavoloDaDb);
			userService.aggiorna(userInSession);
			}
		}
		
		request.getRequestDispatcher("/play/partita.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
