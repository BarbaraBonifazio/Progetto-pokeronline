package it.pokeronline.web.servlet.user;

import java.io.IOException;
import java.util.ArrayList;
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

import it.pokeronline.dto.UserDTO;
import it.pokeronline.model.ruolo.Ruolo;
import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;
import it.pokeronline.service.ruolo.RuoloService;
import it.pokeronline.service.user.UserService;

@WebServlet("/user/ExecuteUpdateUserServlet")
public class ExecuteUpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ExecuteUpdateUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUserInput = request.getParameter("idUserPerUpdate");
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String usernameInput = request.getParameter("username");
		String statoInput = request.getParameter("stato");
		String[] ruoliInput = request.getParameterValues("idRuoli");
		if(ruoliInput == null) {
			ruoliInput = new String[0];
		}
		
		boolean isCreato = false;
		if(StatoUser.valueOf(statoInput) == StatoUser.CREATO) {
			isCreato = true;
		}
		
		List<String> listaRuoli = new ArrayList<String>();
		
		for(String r:ruoliInput){
			listaRuoli.add(r);
		}
		
		UserDTO userDTO = new UserDTO(nomeInput, cognomeInput, usernameInput, listaRuoli);
		
		
		//effettuo la validazione dell'input e se non va bene rimando in pagina
		List<String> userErrors = userDTO.errorsUpdate();
		if (!userErrors.isEmpty()) {
			userDTO.setStato(StatoUser.valueOf(statoInput));
			//lista di enum per lo stato dell'utente 
			List<String> listaStati = Stream.of(StatoUser.values()).map(Enum::name).collect(Collectors.toList());
			request.setAttribute("listaStati", listaStati);
			request.setAttribute("userAttribute", userDTO);
			request.setAttribute("userErrors", userErrors);
			request.setAttribute("idUserPerUpdate", idUserInput);
			isCreato = true;
			request.setAttribute("isCreato", isCreato);
			request.setAttribute("listaRuoli", ruoloService.listAllRuoli());
			request.getRequestDispatcher("/user/update.jsp").forward(request, response);
			return;
		}

	// se arrivo qui significa che va bene
		User userInstance = UserDTO.buildModelFromDto(userDTO);
			
//		if(userInstance.getStato() == StatoUser.ATTIVO) {
//			if (userInstance.getRuoli().size() > 0) {
//				userInstance = userService.findUserWithRuoli(Long.parseLong(idUserInput));
//			} else {
//				for(String r:ruoliInput){
//					Long idRuolo = Long.parseLong(r);
//					Ruolo ruoloNew = new Ruolo();
//					ruoloNew.setId(idRuolo);
//					listaRuoli.add(r);
//					Ruolo ruoloDaDb = ruoloService.caricaSingoloRuolo(idRuolo);
//					userInstance.getRuoli().add(ruoloDaDb);
//				}
//			}
//		} 
//			else {
//			//lista di enum per lo stato dell'utente 
//			List<String> listaStati = Stream.of(StatoUser.values()).map(Enum::name).collect(Collectors.toList());
//			userDTO.setStato(StatoUser.CREATO);
//			request.setAttribute("listaStati", listaStati);
//			request.setAttribute("userErrors", userErrors);
//			request.setAttribute("idUserPerUpdate", idUserInput);
//			
//			request.setAttribute("isCreato", isCreato);
//			request.setAttribute("userAttribute", userDTO);
//			request.setAttribute("listaRuoli", ruoloService.listAllRuoli());
//			userErrors.add("Per poter impostare un ruolo devi prima impostare lo stato dell'utente ad \"ATTIVO\"!");
//			request.getRequestDispatcher("/user/update.jsp").forward(request, response);
//			return;
//		}
		
		
		//Se arrivo qui va tutto bene 
		
		
		
		User userDaDb = userService.findUserWithRuoli(Long.parseLong(idUserInput));
		userDaDb.setNome(userInstance.getNome());
		userDaDb.setCognome(userInstance.getCognome());
		userDaDb.setUsername(userInstance.getUsername());
		userDaDb.setStato(StatoUser.valueOf(statoInput));
		for(String r:ruoliInput){
			Long idRuolo = Long.parseLong(r);
			Ruolo ruoloNew = new Ruolo();
			ruoloNew.setId(idRuolo);
			Ruolo ruoloDaDb = ruoloService.caricaSingoloRuolo(idRuolo);
			userDaDb.getRuoli().add(ruoloDaDb);
		}
		userService.aggiorna(userDaDb);
		request.setAttribute("successMessage", "Aggiornamento avvenuto con successo!");
		List<User> listaUtenti = userService.listAllUsersWithRuoli();
		request.setAttribute("usersPerResults", listaUtenti);

	// andiamo ai risultati
	request.getRequestDispatcher("/user/results.jsp").forward(request, response);
	}

}
