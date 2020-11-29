package it.pokeronline.service.tavolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.pokeronline.model.tavolo.Tavolo;
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

	@Override
	public void aggiorna(Tavolo tavoloInstance) {
		tavoloRepository.save(tavoloInstance);
	}

	@Override
	public void inserisciNuovo(Tavolo tavoloInstance) {
		tavoloRepository.save(tavoloInstance);
	}

	@Override
	public void rimuovi(Tavolo tavoloInstance) {
		tavoloRepository.delete(tavoloInstance);
	}

	@Override
	public List<Tavolo> findByExample(Tavolo example) {
		String query = "select t from Tavolo t where t.id = t.id ";

		if (StringUtils.isNotEmpty(example.getDenominazione()))
			query += " and t.denominazione like '%" + example.getDenominazione() + "%' ";
		if (example.getCifraMin() != null)
			query += " and t.cifraMin = " + example.getCifraMin();
		if (example.getExpMin() != null)
			query += " and t.expMin = " + example.getExpMin();
		if (example.getDataCreazione() != null)
			query += " and t.dataCreazione = " + example.getDataCreazione();

		return entityManager.createQuery(query, Tavolo.class).getResultList();
	}

}
