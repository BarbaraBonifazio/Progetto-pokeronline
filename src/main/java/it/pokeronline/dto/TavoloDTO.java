package it.pokeronline.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.util.Util;

public class TavoloDTO {

	private Long id;
	private String expMin;
	private String cifraMin;
	private String denominazione;
	private String dataCreazione;
	
	public TavoloDTO() {
	}

	public TavoloDTO(String expMin, String cifraMin, String denominazione) {
		super();
		this.expMin = expMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
	}
	
	public TavoloDTO(String cifraMin, String denominazione, String dataCreazione, boolean search) {
		super();
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
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
	
	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.expMin) || !Util.isLong(this.expMin)) {
			result.add("Il campo Esperienza Minima dev'essere valorizzato con un numero");
		} else if(Long.parseLong(this.expMin) < 0){
			result.add("Il campo Esperienza Minima non dev'essere negativo");
		 }	
		if (StringUtils.isBlank(this.cifraMin) || !Util.isInteger(this.cifraMin)) {
			result.add("Il campo Puntata Minima dev'essere valorizzato con un numero decimale");
		} else if(Integer.parseInt(this.cifraMin) <= 0) {
			result.add("Il campo Puntata Minima non dev'essere negativo");
		 }
		if (StringUtils.isBlank(this.denominazione)) {
			result.add("Il campo Denominazione non può essere vuoto");
		}
		return result;
	}
	
	public List<String> errorsSearch() {
		List<String> result = new ArrayList<String>();
		if (this.cifraMin != null && !this.cifraMin.isEmpty()) {
			if (!Util.isInteger(this.cifraMin)) {
				result.add("Il campo Puntata Minima dev'essere valorizzato con un numero decimale");
			} else if(Integer.parseInt(this.cifraMin) <= 0) {
				result.add("Il campo Puntata Minima non dev'essere negativo");
			 }
		}
		if (this.dataCreazione != null && !this.cifraMin.isEmpty()) {
			if (!Util.isDate(this.dataCreazione)) {
				result.add("Il campo Data non è valido");
			}
		}
		return result;
	}

	public static Tavolo buildModelFromDto(TavoloDTO tavoloDTO) {
		Tavolo result = new Tavolo();
		result.setId(tavoloDTO.getId());
		
		//verifico che l'esperienza sia stata valorizzata e passata al DTO per fare il parse, altrimenti setto a null
			Long expMin = null;
			if(tavoloDTO.getExpMin() != null && !"".equals(tavoloDTO.getExpMin())) {
				expMin = Long.parseLong(tavoloDTO.getExpMin());
			}
			
		result.setExpMin(expMin);
		
		//verifico che la Puntata Minima sia stata valorizzata e passata al DTO per fare il parse, altrimenti setto a null
			Integer cifraMin = null;
			if(tavoloDTO.getCifraMin() != null && !"".equals(tavoloDTO.getCifraMin())) {
				cifraMin = Integer.parseInt(tavoloDTO.getCifraMin());
			}
		
		result.setCifraMin(cifraMin);
		result.setDenominazione(tavoloDTO.getDenominazione());
		try {
			//verifico che la data sia stata valorizzata e passata al DTO per fare il parse, altrimenti setto a null
			Date data = null;
			if(tavoloDTO.getDataCreazione() != null && !"".equals(tavoloDTO.getDataCreazione())) {	
			data = new SimpleDateFormat("yyyy-MM-dd").parse(tavoloDTO.getDataCreazione());
			}
			result.setDataCreazione(data);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
