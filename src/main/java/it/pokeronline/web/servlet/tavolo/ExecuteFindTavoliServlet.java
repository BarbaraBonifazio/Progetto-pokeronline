package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.dto.TavoloDTO;
import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;
import it.pokeronline.service.tavolo.TavoloService;
import it.pokeronline.service.user.UserService;

@WebServlet("/tavolo/ExecuteFindTavoliServlet")
public class ExecuteFindTavoliServlet extends HttpServlet {
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
	
    public ExecuteFindTavoliServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String denominazioneInput = request.getParameter("denominazione");
		String cifraMinInput = request.getParameter("cifraMin");
		String dataInput = request.getParameter("data");
		Long userInput = StringUtils.isNumeric(request.getParameter("idUtente"))? Long.parseLong(request.getParameter("idUtente")):null;

		boolean search = true;
		TavoloDTO tavoloDTO = new TavoloDTO(cifraMinInput, denominazioneInput, dataInput, search);
		
			//effettuo la validazione dell'input e se non va bene rimando in pagina
			List<String> tavoloErrors = tavoloDTO.errorsSearch();
			if (!tavoloErrors.isEmpty()) {
				request.setAttribute("tavoloAttribute", tavoloDTO);
				request.setAttribute("tavoloErrors", tavoloErrors);
				request.setAttribute("utentePerSearchTavoli", userInput);
				request.getRequestDispatcher("/tavolo/insert.jsp").forward(request, response);
				return;
			}

		// se arrivo qui significa che va bene
		
		Tavolo tavoloInstance = TavoloDTO.buildModelFromDto(tavoloDTO);
		User userDaDb = userService.caricaSingoloUser(userInput);
		tavoloInstance.setUser(userDaDb);
		request.setAttribute("listaTavoli", tavoloService.findByExample(tavoloInstance));
					 
		RequestDispatcher rd = request.getRequestDispatcher("/tavolo/results.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
