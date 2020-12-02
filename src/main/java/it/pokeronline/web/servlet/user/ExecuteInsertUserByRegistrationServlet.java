package it.pokeronline.web.servlet.user;

import java.io.IOException;
import java.util.Date;
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
import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;
import it.pokeronline.service.user.UserService;

@WebServlet("/ExecuteInsertUserByRegistrationServlet")
public class ExecuteInsertUserByRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private UserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ExecuteInsertUserByRegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String usernameInput = request.getParameter("username");
		String passwordInput = request.getParameter("password");
		String expInput = request.getParameter("exp");
		String creditoInput = request.getParameter("credito");
		expInput = expInput.isEmpty() ? Long.toString(0) : expInput;
		creditoInput = creditoInput.isEmpty() ? Integer.toString(0) : creditoInput;
		UserDTO userDTO = new UserDTO(nomeInput, cognomeInput, usernameInput, passwordInput, expInput, creditoInput);
		
		// effettuo la validazione dell'input e se non va bene rimando in pagina
		List<String> userErrors = userDTO.errors();
		if (!userErrors.isEmpty()) {
			request.setAttribute("userAttribute", userDTO);
			request.setAttribute("userErrors", userErrors);
			request.getRequestDispatcher("/user/registrazione.jsp").forward(request, response);
			return;
		}
	
		//se arrivo qui significa che va bene
		User userInstance = UserDTO.buildModelFromDto(userDTO);
		userInstance.setStato(StatoUser.CREATO);
		userInstance.setExpAccumulata(0L);
		userInstance.setCreditoAccumulato(0);
		Date date = new Date();  
		userInstance.setDataRegistrazione(date);	
		userService.inserisciNuovo(userInstance);
				
		//vado in pagina con ok
		request.setAttribute("successMessage", "Registrazione effettuata!");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
