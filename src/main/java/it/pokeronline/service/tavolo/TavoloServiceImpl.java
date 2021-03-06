package it.pokeronline.service.tavolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;
import it.pokeronline.repository.tavolo.TavoloRepository;

@Component
public class TavoloServiceImpl implements TavoloService {

	@Autowired
	private TavoloRepository tavoloRepository;

	// questo mi serve per il findByExample2 che risulta 'a mano'
	// o comunque in tutti quei casi in cui ho bisogno di costruire custom query nel
	// service
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Tavolo> listAllTavoli() {
		return (List<Tavolo>) tavoloRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Tavolo caricaSingoloTavolo(Long id) {
		return tavoloRepository.findById(id).orElse(null);
	}

	@Transactional
	public boolean aggiorna(Tavolo tavoloInstance) throws Exception {
		boolean result = false;
		Tavolo tavoloDaDB = this.caricaSingoloTavolo(tavoloInstance.getId());
		if(tavoloDaDB.getUsers().size() == 0) {
		tavoloRepository.save(tavoloInstance);
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public void inserisciNuovo(Tavolo tavoloInstance) {
		tavoloRepository.save(tavoloInstance);
	}

	@Transactional
	public boolean rimuovi(Tavolo tavoloInstance) throws Exception {
		boolean result = false;
		Tavolo tavoloDaDB = this.caricaSingoloTavolo(tavoloInstance.getId());
		if(tavoloDaDB.getUsers().size() == 0) {
		tavoloRepository.delete(tavoloInstance);
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public List<Tavolo> findByExample(Tavolo tavolo) {
		
		String query1 = "select t from Tavolo t where t.id = t.id ";
		if (tavolo.getDenominazione() != null) {
			query1 = query1 + " AND t.denominazione like :denominazione ";
		}
		if (tavolo.getCifraMin() != null) {
			query1 = query1 + " AND t.cifraMin = :cifraMin ";
		}
		if (tavolo.getDataCreazione() != null) {
			query1 = query1 + " AND t.dataCreazione = :dataCreazione";
		}
		if (tavolo.getUser() != null) {
			query1 = query1 + " AND t.user = :user ";
		}

		TypedQuery<Tavolo> query2 = entityManager.createQuery(query1, Tavolo.class);
		if (tavolo.getDenominazione() != null) {
			query2.setParameter("denominazione", '%' + tavolo.getDenominazione() + '%');
		}
		if (tavolo.getCifraMin() != null) {
			query2.setParameter("cifraMin", tavolo.getCifraMin());
		}
		if (tavolo.getDataCreazione() != null) {
			query2.setParameter("dataCreazione", tavolo.getDataCreazione());
		}
		if(tavolo.getUser() != null) {
			query2.setParameter("user", tavolo.getUser());
		}
		
		if(tavolo.equals(null)) {
			this.listAllTavoli().toString();
		}
		return query2.getResultList();
	}

	@Override
	public List<Tavolo> findAllByUser_Id(Long id){
		return tavoloRepository.findAllByUser_Id(id);
	}
	
	@Override
	public Tavolo findTavoloWithUtenti(Long id) {
		TypedQuery<Tavolo> query = entityManager.createQuery("select t from Tavolo t LEFT JOIN FETCH t.users where t.id = ?1 ", Tavolo.class);
		query.setParameter(1, id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<Tavolo> findPartite(Tavolo tavolo, User user) {
		
		String query1 = "select distinct t from Tavolo t left join fetch t.user creatore left join t.users giocatori where t.id = t.id ";
		if (tavolo.getDenominazione() != null) {
			query1 = query1 + " AND t.denominazione like :denominazione ";
		}
		if (tavolo.getCifraMin() != null) {
			query1 = query1 + " AND t.cifraMin = :cifraMin ";
		}
		
		if (tavolo.getDataCreazione() != null) {
			query1 = query1 + " AND t.dataCreazione = :dataCreazione";
		}
		if (tavolo.getUser() != null) {
			query1 = query1 + " AND creatore = :user ";
		}
		
		if (tavolo.getUsers() != null && !tavolo.getUsers().isEmpty()) {
			query1 = query1 + " AND giocatori = :users ";
		}
		
		if(user != null) {
		query1 = query1 + " AND t.expMin <= :esperienzaMinimaRichiesta";
		query1 = query1 + " AND t.cifraMin <= :puntataMinimaRichiesta";
		}
		TypedQuery<Tavolo> query2 = entityManager.createQuery(query1, Tavolo.class);
		if (tavolo.getDenominazione() != null) {
			query2.setParameter("denominazione", '%' + tavolo.getDenominazione() + '%');
		}
		if (tavolo.getCifraMin() != null) {
			query2.setParameter("cifraMin", tavolo.getCifraMin());
		}
		if (tavolo.getDataCreazione() != null) {
			query2.setParameter("dataCreazione", tavolo.getDataCreazione());
		}
		if(tavolo.getUser() != null) {
			query2.setParameter("user", tavolo.getUser());
		}
		
		if(tavolo.getUsers() != null && !tavolo.getUsers().isEmpty()) {
			query2.setParameter("users", tavolo.getUsers());
		}
		
		if(user != null) {
			query2.setParameter("esperienzaMinimaRichiesta", user.getExpAccumulata());
			query2.setParameter("puntataMinimaRichiesta", user.getCreditoAccumulato());
		} 
		
		return query2.getResultList();
	}
	
}
