package it.pokeronline.repository.tavolo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pokeronline.model.tavolo.Tavolo;


public interface TavoloRepository extends CrudRepository<Tavolo, Long>,QueryByExampleExecutor <Tavolo> {

	List<Tavolo> findAllByDenominazioneContaining(String term);

}
