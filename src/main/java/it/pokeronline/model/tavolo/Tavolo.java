package it.pokeronline.model.tavolo;

import java.util.Date;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.pokeronline.model.user.User;

@Entity
public class Tavolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long expMin;
	private Double cifraMin;
	private String denominazione;

	@Temporal(TemporalType.DATE)
	private Date dataCreazione = new Date();
	//
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	//
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo", orphanRemoval = true)
	private Set<User> users = new HashSet<>();

	public Tavolo() {
	}
	
	public Tavolo(Long expMin, Double cifraMin, String denominazione, Date dataCreazione) {
		super();
		this.expMin = expMin;
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

	public Long getExpMin() {
		return expMin;
	}

	public void setExpMin(Long expMin) {
		this.expMin = expMin;
	}

	public Double getCifraMin() {
		return cifraMin;
	}

	public void setCifraMin(Double cifraMin) {
		this.cifraMin = cifraMin;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
