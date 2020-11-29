package it.pokeronline.repository.user;

import org.springframework.data.repository.CrudRepository;

import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsernameAndPasswordAndStato(String username,String password, StatoUser stato);

	User findByUsernameAndPassword(String username, String password);
	
	

}
