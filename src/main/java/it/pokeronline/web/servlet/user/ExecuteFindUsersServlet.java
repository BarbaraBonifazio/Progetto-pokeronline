package it.pokeronline.web.servlet.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;
import it.pokeronline.service.user.UserService;


@WebServlet("/ExecuteFindUsersServlet")
public class ExecuteFindUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private UserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ExecuteFindUsersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String statoInput = request.getParameter("stato");
		
		String nomeInput = StringUtils.isNotEmpty(request.getParameter("nome"))? request.getParameter("nome"):null;
		String cognomeInput = StringUtils.isNotEmpty(request.getParameter("cognome"))? request.getParameter("cognome"):null;
		String usernameInput = StringUtils.isNotEmpty(request.getParameter("username"))? request.getParameter("username"):null;
		Long expInput = StringUtils.isNumeric(request.getParameter("expAcc"))? Long.parseLong(request.getParameter("expAcc")):null;
		Double creditoInput = StringUtils.isNumeric(request.getParameter("creditAcc"))? Double.parseDouble(request.getParameter("creditAcc")):null;
		
		try {			
			 Date dataCheck1 = StringUtils.isNotEmpty(request.getParameter("data"))? 
									new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")): null;
								
			User user = new User(nomeInput, cognomeInput, usernameInput, expInput, creditoInput, dataCheck1);
			
			if(StringUtils.isNotEmpty(request.getParameter("data"))) {
			 Date dataCheck2 = StringUtils.isNumeric(request.getParameter("data"))? 
					 			new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")): null;
			user.setDataRegistrazione(dataCheck2);
			}
			
				if (!statoInput.isEmpty() && statoInput != null) {
					user.setStato(StatoUser.valueOf(statoInput));
				}
			
			List<User> listaUtenti = userService.findByExample(user);
			
			request.setAttribute("utentiPerResultsList", listaUtenti);

		// andiamo ai risultati
		request.getRequestDispatcher("resultsListUtenti.jsp").forward(request, response);
		
			} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
