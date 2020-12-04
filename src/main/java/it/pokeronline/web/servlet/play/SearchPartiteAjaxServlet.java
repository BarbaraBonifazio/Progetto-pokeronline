package it.pokeronline.web.servlet.play;

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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.pokeronline.model.ruolo.Codice;
import it.pokeronline.model.user.User;
import it.pokeronline.service.user.UserService;

@WebServlet("/play/SearchPartiteAjaxServlet")
public class SearchPartiteAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public SearchPartiteAjaxServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String term = request.getParameter("term");
		String indice = request.getParameter("indice");
		if(indice.equals("x")){
		List<User> listaCreatori = userService.findCreatori(term, Codice.SPECIAL_PLAYER_ROLE, Codice.ADMIN_ROLE);
		String json = buildJsonResponse(listaCreatori);
		response.getWriter().write(json);
		} else{
			List<User> listaPartecipanti = userService.findGiocatori(term);
				String json = buildJsonResponse(listaPartecipanti);
				response.getWriter().write(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private String buildJsonResponse(List<User> listaUsers) {
		JsonArray ja = new JsonArray();

		for (User userElement : listaUsers) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", userElement.getId());
			jo.addProperty("label", userElement.getUsername());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}

}
