package it.pokeronline.model.ruolo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ruolo {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descrizione;
	
	@Enumerated(EnumType.STRING)
	private Codice codice;
	
	public Ruolo() {
	}

	public Ruolo(String descrizione, Codice codice) {
		this.descrizione = descrizione;
		this.codice = codice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Codice getCodice() {
		return codice;
	}

	public void setCodice(Codice codice) {
		this.codice = codice;
	}

}
