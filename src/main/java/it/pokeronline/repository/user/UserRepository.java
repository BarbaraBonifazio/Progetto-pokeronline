package it.pokeronline.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsernameAndPasswordAndStato(String username,String password, StatoUser stato);

	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
	
	@Query("Select Distinct u From User u LEFT JOIN FETCH u.ruoli r")
	List <User> listAllUsersWithRuoli();

}
