package it.pokeronline.service.user;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.query.Param;

import it.pokeronline.model.ruolo.Codice;
import it.pokeronline.model.ruolo.Ruolo;
import it.pokeronline.model.user.User;

public interface UserService {

	public List<User> listAllUsers();

	public User caricaSingoloUser(Long id);

	public void aggiorna(User userInstance);

	public void inserisciNuovo(User userInstance);

	public void rimuovi(User userInstance);
	
	public List<User> findByExample(User example);
	
	public User eseguiAccesso(String username, String password);
	
	public User eseguiRegistrazione(String username, String password);

	public User findByRuoli(String username, String password);
	
	
}
