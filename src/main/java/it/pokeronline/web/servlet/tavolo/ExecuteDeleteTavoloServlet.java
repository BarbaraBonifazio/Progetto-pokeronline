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
import it.pokeronline.service.tavolo.TavoloService;


@WebServlet("/ExecuteDeleteTavoloServlet")
public class ExecuteDeleteTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private TavoloService tavoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ExecuteDeleteTavoloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idInput = request.getParameter("id");
		
		Tavolo tavoloDaDb = tavoloService.caricaSingoloTavolo(Long.parseLong(idInput));
		try {
			tavoloService.rimuovi(tavoloDaDb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//vado in pagina con ok
		request.setAttribute("successMessage", "Eliminazione avvenuta con successo!");
		request.setAttribute("listaTavoli", tavoloService.listAllTavoli());
		request.getRequestDispatcher("/tavolo/results.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
