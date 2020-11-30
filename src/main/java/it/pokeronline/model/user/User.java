package it.pokeronline.model.user;
	
	import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.pokeronline.model.ruolo.Codice;
import it.pokeronline.model.ruolo.Ruolo;
import it.pokeronline.model.tavolo.Tavolo;

	@Entity
	public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String nome;
		private String cognome;
		private String username;
		private String password;
		private Long expAccumulata;
		private Double creditoAccumulato;
		
		@Temporal(TemporalType.DATE)
		private Date dataRegistrazione = new Date();

		@Enumerated(EnumType.STRING)
		private StatoUser stato = StatoUser.CREATO;

		@ManyToMany
		@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
		private Set<Ruolo> ruoli = new HashSet<>(0);
		//
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
		private Set<Tavolo> tavoli = new HashSet<>();
		//
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "tavolo_id")
		private Tavolo tavolo;
		
		public User() {
		}

		public User(String nome, String cognome, String username, Long expAccumulata,
				Double creditoAccumulato, Date dataRegistrazione) {
			this.nome = nome;
			this.cognome = cognome;
			this.username = username;
			this.expAccumulata = expAccumulata;
			this.creditoAccumulato = creditoAccumulato;
			this.dataRegistrazione = dataRegistrazione;
		}
		
		public User(String nome, String cognome, String username, String password, Long expAccumulata,
				Double creditoAccumulato, Date dataRegistrazione) {
			this.nome = nome;
			this.cognome = cognome;
			this.username = username;
			this.password = password;
			this.expAccumulata = expAccumulata;
			this.creditoAccumulato = creditoAccumulato;
			this.dataRegistrazione = dataRegistrazione;
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

		public Date getDataRegistrazione() {
			return dataRegistrazione;
		}

		public void setDataRegistrazione(Date dataRegistrazione) {
			this.dataRegistrazione = dataRegistrazione;
		}

		public StatoUser getStato() {
			return stato;
		}

		public void setStato(StatoUser stato) {
			this.stato = stato;
		}

		public Set<Ruolo> getRuoli() {
			return ruoli;
		}

		public void setRuoli(Set<Ruolo> ruoli) {
			this.ruoli = ruoli;
		}

		public Long getExpAccumulata() {
			return expAccumulata;
		}

		public void setExpAccumulata(Long expAccumulata) {
			this.expAccumulata = expAccumulata;
		}

		public Double getCreditoAccumulato() {
			return creditoAccumulato;
		}

		public void setCreditoAccumulato(Double creditoAccumulato) {
			this.creditoAccumulato = creditoAccumulato;
		}

		public Tavolo getTavolo() {
			return tavolo;
		}

		public void setTatolo(Tavolo tavolo) {
			this.tavolo = tavolo;
		}

		public Set<Tavolo> getTavoli() {
			return tavoli;
		}

		public void setTavoli(Set<Tavolo> tavoli) {
			this.tavoli = tavoli;
		}

		public boolean isAdmin() {
			for (Ruolo ruoloItem : ruoli) {
				if (ruoloItem.getCodice().equals(Codice.ADMIN_ROLE))
					return true;
			}
			return false;
		}	
		
		public boolean isSpecialPlayer() {
			for (Ruolo ruoloItem : ruoli) {
				if (ruoloItem.getCodice().equals(Codice.SPECIAL_PLAYER_ROLE))
					return true;
			}
			return false;
		}	
		
		public boolean isPlayer() {
			for (Ruolo ruoloItem : ruoli) {
				if (ruoloItem.getCodice().equals(Codice.PLAYER_ROLE))
					return true;
			}
			return false;
		}	
}
