package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;
import it.pokeronline.service.tavolo.TavoloService;

@WebServlet("/tavolo/ConfirmDeleteTavoloServlet")
public class ConfirmDeleteTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private TavoloService tavoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ConfirmDeleteTavoloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTavoloInput = request.getParameter("idDaInviareAExecuteDelete");
		String idUtenteCreatore = request.getParameter("idUser");
		
		Tavolo tavoloDaDb = tavoloService.findTavoloWithUtenti(Long.parseLong(idTavoloInput));
		if(tavoloDaDb.getUsers().size() > 0) {
			request.setAttribute("errorMessage", "Il tuo tavolo è attualmente occupato da almeno un giocatore, non puoi eliminarlo!");
			User userInSession = (User)request.getSession().getAttribute("user");
			request.setAttribute("listaTavoli", tavoloService.findAllByUser_Id(userInSession.getId()));
			request.getRequestDispatcher("/tavolo/results.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("idUserAttribute", idUtenteCreatore);
		request.setAttribute("tavoloAttribute", tavoloService.caricaSingoloTavolo(Long.parseLong(idTavoloInput)));
		request.getRequestDispatcher("/tavolo/confirmDelete.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
