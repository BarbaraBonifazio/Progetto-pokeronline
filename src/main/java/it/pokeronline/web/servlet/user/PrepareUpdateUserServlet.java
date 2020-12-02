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
import it.pokeronline.model.user.User;
import it.pokeronline.service.ruolo.RuoloService;
import it.pokeronline.service.user.UserService;

@WebServlet("/user/PrepareUpdateUserServlet")
public class PrepareUpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private UserService userService;

	@Autowired
	private RuoloService ruoloService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public PrepareUpdateUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUser = request.getParameter("idDaInviareAExecuteUpdate");
		User userDaDb = userService.findUserWithRuoli(Long.parseLong(idUser));
		boolean isCreato = false;
		if(userDaDb.getStato() == StatoUser.CREATO) {
			isCreato = true;
		}
		
		request.setAttribute("listaRuoli", ruoloService.listAllRuoli());
		//lista di enum per lo stato dell'utente 
		List<String> listaStati = Stream.of(StatoUser.values()).map(Enum::name).collect(Collectors.toList());
		request.setAttribute("listaStati", listaStati);
		request.setAttribute("idUserPerUpdate", idUser);
		request.setAttribute("userAttribute", userDaDb);
		request.setAttribute("isCreato", isCreato);
		request.getRequestDispatcher("/user/update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
