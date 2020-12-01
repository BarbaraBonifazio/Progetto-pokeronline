package it.pokeronline.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.pokeronline.model.user.User;


@WebFilter(filterName = "CheckAuthFilter", urlPatterns = { "/*" })
public class CheckAuthFilter implements Filter {

	private static final String HOME_PATH = "";
	private static final String[] EXCLUDED_URLS = {"/login.jsp","/LoginServlet","/LogoutServlet","/assets/", "/mystyle/",
			"/PrepareInsertUserByRegistrationServlet", "/ExecuteInsertUserByRegistrationServlet", "/user/registrazione.jsp",
			"/PrepareInsertUserByRegistrationServlet", "/ExecuteInsertUserByRegistrationServlet"};
	private static final String[] PROTECTED_URLS_ADMIN = {"/user/"};
	private static final String[] PROTECTED_URLS_TAVOLO = {"/tavolo/"};

	public CheckAuthFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		//prendo il path della request che sta passando in questo momento
		String pathAttuale = httpRequest.getServletPath();
		
		//vediamo se il path risulta tra quelli 'liberi di passare'
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);
		
		//se non lo e' bisogna controllare sia sessione che percorsi protetti
		if (!isInWhiteList) {
			User userInSession = (User)httpRequest.getSession().getAttribute("user");
			//intanto verifico se utente in sessione
			if (userInSession == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath());
				return;
			} 
			//controllo che utente abbia ruolo admin se nel path risulta presente /user/
			if(isPathForOnlyAdministrators(pathAttuale) && !userInSession.isAdmin()) {
				httpRequest.setAttribute("messaggio", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/home.jsp").forward(httpRequest, httpResponse);
				return;
			}
			
			//controllo che utente abbia ruolo specialPlayer se nel path risulta presente /tavolo/
			if(isPathForOnlySpecialPlayers(pathAttuale) && userInSession.isPlayer()) {
				httpRequest.setAttribute("messaggio", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/home.jsp").forward(httpRequest, httpResponse);
				return;
			}
		}

		chain.doFilter(request, response);
	}
	
	private boolean isPathInWhiteList(String requestPath) {
		//bisogna controllare che se il path risulta proprio "" oppure se 
		//siamo in presenza un url 'libero'
		if(requestPath.equals(HOME_PATH))
			return true;
		
		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS_ADMIN) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPathForOnlySpecialPlayers(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS_TAVOLO) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	public void destroy() {
	}

}
