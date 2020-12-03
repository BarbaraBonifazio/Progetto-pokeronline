package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.service.tavolo.TavoloService;
import it.pokeronline.service.user.UserService;

@WebServlet("/tavolo/ExecuteListAllTavoliByCreatoreServlet")
public class ExecuteListAllTavoliByCreatoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		@Autowired
		UserService userService;
		
		@Autowired
		TavoloService tavoloService;
	   
	   @Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		}
	
    public ExecuteListAllTavoliByCreatoreServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUserInput = request.getParameter("idUserCreatore");
			
		request.setAttribute("listaTavoli", tavoloService.findAllByUser_Id(Long.parseLong(idUserInput)));
		
		RequestDispatcher rd = request.getRequestDispatcher("/tavolo/results.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
