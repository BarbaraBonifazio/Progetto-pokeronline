package it.pokeronline.service.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;
import it.pokeronline.repository.user.UserRepository;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	// questo mi serve per il findByExample2 che risulta 'a mano'
	// o comunque in tutti quei casi in cui ho bisogno di costruire custom query nel service
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly = true)
	public List<User> listAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public User caricaSingoloUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(User userInstance) {
		userRepository.save(userInstance);		
	}

	@Transactional
	public void inserisciNuovo(User userInstance) {
		userRepository.save(userInstance);		
	}

	@Transactional
	public void rimuovi(User userInstance) {
		userRepository.delete(userInstance);
	}

	@Override
	public List<User> findByExample(User user) {
					
		String query1 = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.ruoli r where u.id = u.id ";
		if (user.getNome() != null && !user.getNome().isEmpty()) {
			query1 = query1 + " AND u.nome like :nome ";
		}
		if (user.getCognome() != null && !user.getCognome().isEmpty()) {
			query1 = query1 + " AND u.cognome like :cognome ";
		}
		if (user.getUsername() != null && !user.getUsername().isEmpty()) {
			query1 = query1 + " AND u.username like :username ";
		}
		if (user.getDataRegistrazione() != null) {
			query1 = query1 + " AND u.dataRegistrazione = :dataRegistrazione ";
		}
		if (user.getStato() != null) {
			query1 = query1 + " AND u.stato = :stato ";
		}
		if (user.getRuoli() != null && !user.getRuoli().isEmpty()) {
			query1 = query1 + " AND r in (:ruoli)";
		}

		TypedQuery<User> query2 = entityManager.createQuery(query1, User.class);
		if (user.getNome() != null && !user.getNome().isEmpty()) {
			query2.setParameter("nome", '%' + user.getNome() + '%');
		}
		if (user.getCognome() != null && !user.getCognome().isEmpty()) {
			query2.setParameter("cognome", '%' + user.getCognome() + '%');
		}
		if (user.getUsername() != null && !user.getUsername().isEmpty()) {
			query2.setParameter("username", '%' + user.getUsername() + '%');
		}
		if (user.getDataRegistrazione() != null) {
			query2.setParameter("dataRegistrazione", user.getDataRegistrazione());
		}
		if (user.getStato() != null) {
			query2.setParameter("stato", user.getStato());
		}
		if (user.getRuoli() != null && !user.getRuoli().isEmpty()) {
			query2.setParameter("ruoli", user.getRuoli());
		}
		if(user.equals(null)) {
			this.listAllUsers().toString();
		}
		return query2.getResultList();
	}

	@Override
	public User eseguiAccesso(String username, String password) {
		return userRepository.findByUsernameAndPasswordAndStato(username, password,StatoUser.ATTIVO);
	}

	@Override
	public User eseguiRegistrazione(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
	@Override
	public User checkRuoli(String username, String password) {
		TypedQuery<User> query = entityManager.createQuery("select u from User u JOIN FETCH u.ruoli where u.username = ?1 and u.password = ?2", User.class);
		query.setParameter(1, username);
		query.setParameter(2, password);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public User findUserWithRuoli(Long id) {
		TypedQuery<User> query = entityManager.createQuery("select u from User u LEFT JOIN FETCH u.ruoli where u.id = ?1 ", User.class);
		query.setParameter(1, id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<User> listAllUsersWithRuoli() {
		return userRepository.listAllUsersWithRuoli();
	}
	
}
