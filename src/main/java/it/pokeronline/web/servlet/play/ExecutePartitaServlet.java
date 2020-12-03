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

@WebServlet("/play/ExecutePartitaServlet")
public class ExecutePartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Autowired
	UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ExecutePartitaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User giocatore = (User)request.getSession().getAttribute("user");
		
		if(giocatore.getCreditoAccumulato() < giocatore.getTavolo().getCifraMin()) {
			request.setAttribute("errorMessage", "Il tuo credito a disposizione è inferiore alla puntata minima richiesta per giocare!");
			request.getRequestDispatcher("/play/partita.jsp").forward(request, response);
			return;
		}

		double segno = Math.random();
		if(segno >= 0.5) {
			segno = 1;
		} else {
			segno = -1;
		}
		
		Integer somma = (int)(Math.random()*1000);
		Integer Tot = (int)segno * somma;
		
		Integer creditoUser = giocatore.getCreditoAccumulato();
		giocatore.setCreditoAccumulato(creditoUser + Tot);
		

		if(creditoUser < 0) {
			giocatore.setCreditoAccumulato(0);
			userService.aggiorna(giocatore);
			request.setAttribute("errorMessage", "Hai esaurito il credito a disposizione per giocare!");
			request.getRequestDispatcher("/play/partita.jsp").forward(request, response);
			return;
		}
		
		Long expGioco = giocatore.getExpAccumulata();
		expGioco ++;
		giocatore.setExpAccumulata(expGioco);
		userService.aggiorna(giocatore);
		
		if(Tot >= 0) {
			request.setAttribute("successMessage", "Complimenti! Hai vinto " + Tot + " euro!");
			request.getRequestDispatcher("/play/partita.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Sei stato sfortunato... " + " hai perso " + Tot + " euro ");
			request.getRequestDispatcher("/play/partita.jsp").forward(request, response);
			}
		}

}
