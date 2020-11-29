package it.pokeronline.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.util.Util;

public class TavoloDTO {

	private Long id;
	private String expMin;
	private String cifraMin;
	private String denominazione;

	public TavoloDTO() {
	}

	public TavoloDTO(String expMin, String cifraMin, String denominazione) {
		super();
		this.expMin = expMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpMin() {
		return expMin;
	}

	public void setExpMin(String expMin) {
		this.expMin = expMin;
	}

	public String getCifraMin() {
		return cifraMin;
	}

	public void setCifraMin(String cifraMin) {
		this.cifraMin = cifraMin;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.expMin) || !Util.isLong(this.expMin))
			result.add("Il campo Esperienza Minima dev'essere valorizzato con un numero");
		if (StringUtils.isBlank(this.cifraMin) || !Util.isDouble(this.cifraMin))
			result.add("Il campo Cifra Minima dev'essere valorizzato con un numero decimale");
		if (StringUtils.isBlank(this.denominazione))
			result.add("Il campo Denominazione non può essere vuoto");
		return result;
	}

	public static Tavolo buildModelFromDto(TavoloDTO tavoloDTO) {
		Tavolo result = new Tavolo();
		result.setId(tavoloDTO.getId());
		result.setExpMin(Long.parseLong(tavoloDTO.getExpMin()));
		result.setCifraMin(Double.parseDouble(tavoloDTO.getCifraMin()));
		result.setDenominazione(tavoloDTO.getDenominazione());
		return result;
	}
}
