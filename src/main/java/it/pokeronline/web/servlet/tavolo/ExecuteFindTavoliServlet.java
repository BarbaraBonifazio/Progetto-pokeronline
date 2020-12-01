package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		String denominazioneInput = StringUtils.isNotEmpty(request.getParameter("denominazione"))? request.getParameter("denominazione"):null;
		Double cifraMinInput = StringUtils.isNumeric(request.getParameter("cifraMin"))? Double.parseDouble(request.getParameter("cifraMin")):null;
		Long userInput = StringUtils.isNumeric(request.getParameter("idUtente"))? Long.parseLong(request.getParameter("idUtente")):null;

		try {
			Date dateInput = StringUtils.isNotEmpty(request.getParameter("data"))? 
								new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")): null;
								
			Tavolo tavolo = new Tavolo(cifraMinInput, denominazioneInput, dateInput);

			 User userDaDb = userService.caricaSingoloUser(userInput);
			 tavolo.setUser(userDaDb);
			 request.setAttribute("listaTavoli", tavoloService.findByExample(tavolo));
			 
		RequestDispatcher rd = request.getRequestDispatcher("/tavolo/results.jsp");
		rd.forward(request, response);
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
