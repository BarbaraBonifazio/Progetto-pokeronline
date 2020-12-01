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

import it.pokeronline.service.tavolo.TavoloService;


@WebServlet("/tavolo/PrepareUpdateTavoloServlet")
public class PrepareUpdateTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TavoloService tavoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public PrepareUpdateTavoloServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUtenteCreatore = request.getParameter("idUser");
		String idTavoloInput = request.getParameter("idDaInviareAExecuteUpdate");
		
		request.setAttribute("idUserPerUpdateTavolo", idUtenteCreatore);
		request.setAttribute("idTavoloPerUpdate", idTavoloInput);
		request.setAttribute("tavoloAttribute", tavoloService.caricaSingoloTavolo(Long.parseLong(idTavoloInput)));
		request.getRequestDispatcher("/tavolo/update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
