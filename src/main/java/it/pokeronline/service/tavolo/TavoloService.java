package it.pokeronline.service.tavolo;

import java.util.List;

import it.pokeronline.model.tavolo.Tavolo;

public interface TavoloService {
	public List<Tavolo> listAllTavoli() ;

	public Tavolo caricaSingoloTavolo(Long id);

	public boolean aggiorna(Tavolo tavoloInstance) throws Exception;

	public void inserisciNuovo(Tavolo tavoloInstance);

	public boolean rimuovi(Tavolo tavoloInstance) throws Exception;
	
	public List<Tavolo> findByExample(Tavolo example);

	public List<Tavolo> findAllByUser_Id(Long id);
}
