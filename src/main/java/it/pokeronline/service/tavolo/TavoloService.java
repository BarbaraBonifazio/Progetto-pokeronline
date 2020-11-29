package it.pokeronline.service.tavolo;

import java.util.List;

import it.pokeronline.model.tavolo.Tavolo;

public interface TavoloService {
	public List<Tavolo> listAllTavoli() ;

	public Tavolo caricaSingoloTavolo(Long id);

	public void aggiorna(Tavolo tavoloInstance);

	public void inserisciNuovo(Tavolo tavoloInstance);

	public void rimuovi(Tavolo tavoloInstance);
	
	public List<Tavolo> findByExample(Tavolo example);
}
