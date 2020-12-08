package it.pokeronline.web.servlet.play;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/play/ExecuteSearchPartiteServlet")
public class ExecuteSearchPartiteServlet extends HttpServlet {
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

	public ExecuteSearchPartiteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String denominazioneInput = request.getParameter("denominazione");
		String cifraMinInput = request.getParameter("cifraMin");
		String dataInput = request.getParameter("data");
		String usernameCreatoreRicercato = request.getParameter("creatoreInput");
		String usernameGiocatoreRicercato = request.getParameter("partecipanteInput");

		User userGiocatoreInSession = (User) request.getSession().getAttribute("user");

		boolean search = true;
		TavoloDTO tavoloDTO = new TavoloDTO(cifraMinInput, denominazioneInput, dataInput, search);

		// effettuo la validazione dell'input e se non va bene rimando in pagina
		List<String> tavoloErrors = tavoloDTO.errorsSearch();
		if (!tavoloErrors.isEmpty()) {
			request.setAttribute("tavoloAttribute", tavoloDTO);
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/play/searchPartite.jsp").forward(request, response);
			return;
		}

		// se arrivo qui significa che va bene

		Tavolo tavoloInstance = TavoloDTO.buildModelFromDto(tavoloDTO);
		if (usernameCreatoreRicercato == null) {
			request.setAttribute("creatoreInput", usernameCreatoreRicercato);
			tavoloErrors.add("Il campo Creatore non è valido!");
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/play/searchPartite.jsp").forward(request, response);
			return;
		} else if (!usernameCreatoreRicercato.isEmpty()) {
			User userCreatoreDaDb = userService.findByUsername(usernameCreatoreRicercato);
			tavoloInstance.setUser(userCreatoreDaDb);
		}
		if (usernameGiocatoreRicercato == null) {
			request.setAttribute("partecipanteInput", usernameGiocatoreRicercato);
			tavoloErrors.add("Il campo Partecipante non è valido!");
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/play/searchPartite.jsp").forward(request, response);
			return;
		} else if (!usernameGiocatoreRicercato.isEmpty()) {
			User userGiocatoreDaDb = userService.findByUsername(usernameGiocatoreRicercato);
			tavoloInstance.getUsers().add(userGiocatoreDaDb);
		}

		request.setAttribute("listaTavoli", tavoloService.findPartite(tavoloInstance, userGiocatoreInSession));

		RequestDispatcher rd = request.getRequestDispatcher("/play/results.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
