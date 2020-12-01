package it.pokeronline.web.servlet.user;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.model.user.StatoUser;
import it.pokeronline.service.ruolo.RuoloService;


@WebServlet("/user/PrepareFindUsersServlet")
public class PrepareFindUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RuoloService ruoloService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public PrepareFindUsersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.setAttribute("listaRuoli", ruoloService.listAllRuoli());
			//lista di enum per lo stato dell'utente 
			List<String> listaStati = Stream.of(StatoUser.values()).map(Enum::name).collect(Collectors.toList());
			request.setAttribute("listaStati", listaStati);

		request.getRequestDispatcher("/user/searchUsers.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
