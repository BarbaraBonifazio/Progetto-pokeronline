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

import it.pokeronline.dto.UserDTO;
import it.pokeronline.model.ruolo.Ruolo;
import it.pokeronline.model.user.User;
import it.pokeronline.service.ruolo.RuoloService;
import it.pokeronline.service.user.UserService;


@WebServlet("/user/ExecuteFindUsersServlet")
public class ExecuteFindUsersServlet extends HttpServlet {
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
	
    public ExecuteFindUsersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String usernameInput = request.getParameter("username");
		String dataInput = request.getParameter("data");
		String statoInput = request.getParameter("stato");
		String ruoloInput = request.getParameter("idRuolo");
		
		UserDTO userDTO = new UserDTO(nomeInput, cognomeInput, usernameInput, dataInput, statoInput);
			
			//effettuo la validazione dell'input e se non va bene rimando in pagina
			List<String> userErrors = userDTO.errorsSearch();
			if (!userErrors.isEmpty()) {
				request.setAttribute("userAttribute", userDTO);
				request.setAttribute("userErrors", userErrors);
				request.getRequestDispatcher("/user/searchUsers.jsp").forward(request, response);
				return;
			}

		// se arrivo qui significa che va bene
			User userInstance = UserDTO.buildModelFromDto(userDTO);
			
//			if (statoInput != null && !statoInput.isEmpty()) {
//				userInstance.setStato(StatoUser.valueOf(statoInput));
//			}
			if (ruoloInput != null && !ruoloInput.isEmpty()) {
				Ruolo ruoloDaDb = ruoloService.caricaSingoloRuolo(Long.parseLong(ruoloInput));
				userInstance.getRuoli().add(ruoloDaDb);
			}
			
			List<User> listaUtenti = userService.findByExample(userInstance);
			
			request.setAttribute("usersPerResults", listaUtenti);

		// andiamo ai risultati
		request.getRequestDispatcher("/user/results.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
