package it.pokeronline.service.user;

import java.util.List;

import it.pokeronline.model.ruolo.Codice;
import it.pokeronline.model.user.User;

public interface UserService {

	public List<User> listAllUsers();

	public User caricaSingoloUser(Long id);

	public void aggiorna(User userInstance);

	public void inserisciNuovo(User userInstance);

	public void rimuovi(User userInstance);
	
	public List<User> findByExample(User user);
	
	public User eseguiAccesso(String username, String password);
	
	public User eseguiRegistrazione(String username, String password);

	public User userWithRuoliAndTavolo(String username, String password);

	public User findUserWithRuoli(Long id);

	public List <User> listAllUsersWithRuoli();

	public User findByUsername(String username);

	public List<User> findCreatori(String username, Codice codiceRuoloUserSpecialPlayer, Codice codiceRuoloUserAdmin);

	public List<User> findGiocatori(String username);

	

}
