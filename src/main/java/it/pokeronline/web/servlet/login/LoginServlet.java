package it.pokeronline.web.servlet.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.model.user.User;
import it.pokeronline.service.user.UserService;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public LoginServlet() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernameInput = request.getParameter("username");
		String passwordInput = request.getParameter("password");
		
		User userInesistente = userService.eseguiRegistrazione(usernameInput, passwordInput);
		User userCheAccede = userService.eseguiAccesso(usernameInput, passwordInput);
		User user = userService.findByRuoli(usernameInput, passwordInput);
		
		//se non trovo nulla non deve essere possibile accedere
		if(userInesistente == null) {
			request.setAttribute("errorMessage", "Non risulti registrato!");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		
		//se non trovo nulla non deve essere possibile accedere
		if(userCheAccede == null) {
			request.setAttribute("errorMessage", "Per accedere devi essere prima abilitato!");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		//metto utente in sessione
		HttpSession session =  request.getSession();
		session.setAttribute("user", user);
		session.setAttribute("isAdmin", user.isAdmin());
		session.setAttribute("isSpecialPlayer", user.isSpecialPlayer());
		session.setAttribute("isPlayer", user.isPlayer());
		
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
	}

}
