package it.pokeronline.service.ruolo;

import java.util.List;

import it.pokeronline.model.ruolo.Ruolo;

public interface RuoloService {

	public List<Ruolo> listAllRuoli() ;

	public Ruolo caricaSingoloRuolo(Long id);
	
}
