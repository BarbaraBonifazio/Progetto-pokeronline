package it.pokeronline.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.pokeronline.model.user.User;
import it.pokeronline.util.Util;

public class UserDTO {

	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String expAccumulata;
	private String creditoAccumulato;
	private String dataRegistrazione;

	public UserDTO() {
	}

	public UserDTO(String nome, String cognome, String username, String dataRegistrazione, boolean search) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.dataRegistrazione = dataRegistrazione;
	}
	
	public UserDTO(String nome, String cognome, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}
	
	public UserDTO(String nome, String cognome, String username, String password, String expAccumulata,
			String creditoAccumulato) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.expAccumulata = expAccumulata;
		this.creditoAccumulato = creditoAccumulato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExpAccumulata() {
		return expAccumulata;
	}

	public void setExpAccumulata(String expAccumulata) {
		this.expAccumulata = expAccumulata;
	}

	public String getCreditoAccumulato() {
		return creditoAccumulato;
	}

	public void setCreditoAccumulato(String creditoAccumulato) {
		this.creditoAccumulato = creditoAccumulato;
	}
	
	public String getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.nome))
			result.add("Il campo Nome non può essere vuoto");
		if (StringUtils.isBlank(this.cognome))
			result.add("Il campo Cognome non può essere vuoto");
		if (StringUtils.isBlank(this.username))
			result.add("Il campo Username non può essere vuoto");
		if (StringUtils.isBlank(this.password))
			result.add("Il campo Password non può essere vuoto");
		if (StringUtils.isBlank(this.expAccumulata) || !Util.isLong(this.expAccumulata))
			result.add("Il campo Esperienza Accumulata dev'essere valorizzato con un numero");
		if (StringUtils.isBlank(this.creditoAccumulato) || !Util.isDouble(this.creditoAccumulato))
			result.add("Il campo Credito Accumulato dev'essere valorizzato con un numero decimale");
		return result;
	}
	
	public List<String> errorsSearch() {
		List<String> result = new ArrayList<String>();
	
		if (this.dataRegistrazione != null && !this.dataRegistrazione.isEmpty()) {
			if (!Util.isDate(this.dataRegistrazione)) {
				result.add("Il campo Data non è valido");
			}
		}
		return result;
	}

	public static User buildModelFromDto(UserDTO userDTO) {
		User result = new User();
		result.setId(userDTO.getId());
		result.setNome(userDTO.getNome());
		result.setCognome(userDTO.getCognome());
		result.setUsername(userDTO.getUsername());
		result.setPassword(userDTO.getPassword());
		
		//verifico che l'esperienza sia stata valorizzata e passata al DTO per fare il parse, altrimenti setto a null
		Long expAcc = null;
		if(userDTO.getExpAccumulata() != null && !"".equals(userDTO.getExpAccumulata())) {
			expAcc = Long.parseLong(userDTO.getExpAccumulata());
		}
		
		result.setExpAccumulata(expAcc);
		
		//verifico che il Credito Accumulato sia stato valorizzato e passato al DTO per fare il parse, altrimenti setto a null
		Integer creditAcc = null;
		if(userDTO.getCreditoAccumulato() != null && !"".equals(userDTO.getCreditoAccumulato())) {
			creditAcc = Integer.parseInt(userDTO.getCreditoAccumulato());
		}
	
		result.setCreditoAccumulato(creditAcc);
		
		try {
			//verifico che la data sia stata valorizzata e passata al DTO per fare il parse, altrimenti setto a null
			Date data = null;
			if(userDTO.getDataRegistrazione() != null && !"".equals(userDTO.getDataRegistrazione())) {	
			data = new SimpleDateFormat("yyyy-MM-dd").parse(userDTO.getDataRegistrazione());
			}
			
		result.setDataRegistrazione(data);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
