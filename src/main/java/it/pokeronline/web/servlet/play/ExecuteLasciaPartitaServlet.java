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
		
		User giocatore = (User)request.getSession().getAttribute("user");

		Long expGioco = giocatore.getExpAccumulata();
		expGioco ++;
		giocatore.setExpAccumulata(expGioco);
		
		giocatore.setTatolo(null);
		
		userService.aggiorna(giocatore);
		
		request.setAttribute("successMessage", "Hai abbandonato la partita ");
		request.getRequestDispatcher("/play/homePlay.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
