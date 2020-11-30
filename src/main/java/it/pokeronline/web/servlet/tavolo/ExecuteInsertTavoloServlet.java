package it.pokeronline.web.servlet.tavolo;

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

import it.pokeronline.dto.TavoloDTO;
import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;
import it.pokeronline.service.tavolo.TavoloService;
import it.pokeronline.service.user.UserService;


@WebServlet("/ExecuteInsertTavoloServlet")
public class ExecuteInsertTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private TavoloService tavoloService;

	@Autowired
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ExecuteInsertTavoloServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String expMinInput = request.getParameter("expMin");
		String cifraMinInput = request.getParameter("cifraMin");
		String denominazioneInput = request.getParameter("denominazione");
		String idUserInput = request.getParameter("idUser");

		TavoloDTO tavoloDTO = new TavoloDTO(expMinInput, cifraMinInput, denominazioneInput);
		User userDaDB = userService.caricaSingoloUser(Long.parseLong(idUserInput));
		
		//effettuoi la validazione dell'input e se non va bene rimando in pagina
				List<String> tavoloErrors = tavoloDTO.errors();
				if (!tavoloErrors.isEmpty()) {
					request.setAttribute("tavoloAttribute", tavoloDTO);
					request.setAttribute("tavoloErrors", tavoloErrors);
					request.setAttribute("idUserPerInsertTavolo", userDaDB);
					request.getRequestDispatcher("/tavolo/insert.jsp").forward(request, response);
					return;
				}
		
		// se arrivo qui significa che va bene
		Tavolo tavoloInstance = TavoloDTO.buildModelFromDto(tavoloDTO);
		tavoloInstance.setUser(userDaDB);

		request.setAttribute("userAttribute", idUserInput);
		tavoloService.inserisciNuovo(tavoloInstance);

		// vado in pagina con ok
		request.setAttribute("messaggioConferma", "Inserimento avvenuto con successo");
		request.setAttribute("listaTavoli", tavoloService.listAllTavoli());
		request.getRequestDispatcher("/tavolo/results.jsp").forward(request, response);
	}

}
