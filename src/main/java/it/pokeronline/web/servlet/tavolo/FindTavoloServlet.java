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

@WebServlet("/tavolo/FindTavoloServlet")
public class FindTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	@Autowired
	private TavoloService tavoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public FindTavoloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idTavoloInput = request.getParameter("idParamPerDettaglioTavolo");
		String idUserPerSearch = request.getParameter("idUser");
		
//		//palleggio i parametri per tornare alla ricerca effettuata
//		String expMinInput = request.getParameter("expMinPerTornareAllaRicercaEffettuata");
//		String cifraMinInput = request.getParameter("cifraMinPerTornareAllaRicercaEffettuata");
//		String denominazioneInput = request.getParameter("denominazionePerTornareAllaRicercaEffettuata");
//		String dataCreazioneInput = request.getParameter("dataCreazionePerTornareAllaRicercaEffettuata");
		
//		try {
//			
//			if (idTavoloInput.matches("[0-9]+")) {
//				
//				if(idTavoloInput == null || idTavoloInput.isEmpty()) {
//					
//					request.setAttribute("errorMessage", "Attenzione non è stato inserito un tavolo valido!");
//					request.setAttribute("listaTavoli",
//							tavoloService.findByExample(new Tavolo(expMinInput, cifraMinInput, denominazioneInput, dataCreazioneInput)));
//					request.getRequestDispatcher("/tavolo/results.jsp").forward(request, response);
//					return;
//				} else {
//					
//					
//				Tavolo tavoloDaDb = tavoloService.caricaSingoloTavolo(Long.parseLong(idTavoloInput));
//				
//					// Valido eventuale parametro passato da URL 
//					if (tavoloDaDb == null) {
//						request.setAttribute("errorMessage", "Attenzione non è stato inserito un tavolo valido!");
//						request.setAttribute("listaTavoli",
//								tavoloService.findByExample(new Tavolo(expMinInput, cifraMinInput, denominazioneInput, dataCreazioneInput)));
//						request.getRequestDispatcher("/tavolo/results.jsp").forward(request, response);
//						return;
//					}
//				}
//			} else {
//				request.setAttribute("errorMessage", "Attenzione l'id del tavolo che hai inserito non è valido!");
//				request.setAttribute("listaTavoli",
//						tavoloService.findByExample(new Tavolo(expMinInput, cifraMinInput, denominazioneInput, dataCreazioneInput)));
//				request.getRequestDispatcher("/tavolo/results.jsp").forward(request, response);
//				return;
//			} 
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// --fine validazione parametro da url--
		
		request.setAttribute("idUserPerSearch", idUserPerSearch);
		request.setAttribute("tavoloPerShow", tavoloService.caricaSingoloTavolo(Long.parseLong(idTavoloInput)));
		request.getRequestDispatcher("/tavolo/show.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
