package it.pokeronline.service.tavolo;

import java.util.List;

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;

public interface TavoloService {
	public List<Tavolo> listAllTavoli() ;

	public Tavolo caricaSingoloTavolo(Long id);

	public boolean aggiorna(Tavolo tavoloInstance) throws Exception;

	public void inserisciNuovo(Tavolo tavoloInstance);

	public boolean rimuovi(Tavolo tavoloInstance) throws Exception;
	
	public List<Tavolo> findByExample(Tavolo example);

	public List<Tavolo> findAllByUser_Id(Long id);

	public Tavolo findTavoloWithUtenti(Long id);

	public List<Tavolo> findPartite(Tavolo tavolo, User user);

}
