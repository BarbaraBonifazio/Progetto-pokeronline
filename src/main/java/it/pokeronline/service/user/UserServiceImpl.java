package it.pokeronline.service.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
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
	public List<User> findByExample(User example) {
		String query = "select u from User u where u.id = u.id ";

		if (StringUtils.isNotEmpty(example.getNome()))
			query += " and u.nome like '%" + example.getNome() + "%' ";
		if (StringUtils.isNotEmpty(example.getCognome()))
			query += " and u.cognome like '%" + example.getCognome() + "%' ";
		if (StringUtils.isNotEmpty(example.getUsername()))
			query += " and u.username like '%" + example.getUsername() + "%' ";
		if (example.getStato() != null)
			query += " and u.stato = " + example.getStato();

		return entityManager.createQuery(query, User.class).getResultList();
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
	public User findByRuoli(String username, String password) {
		TypedQuery<User> query = entityManager.createQuery("select u from User u JOIN FETCH u.ruoli where u.username = ?1 and u.password = ?2", User.class);
		query.setParameter(1, username);
		query.setParameter(2, password);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
