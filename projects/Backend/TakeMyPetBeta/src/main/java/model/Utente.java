package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Utente {
	
	private String tipoUtente;
	private Integer contatoreAccessiSbagliati = 0;
	
	
	private String nome;
	private String cognome;
	@Id
	private String username;
	@JsonIgnore
	private String password;
	@Temporal(TemporalType.DATE)
	private Date dataDiNascita;
	@Temporal(TemporalType.DATE)
	private Date dataRegistrazione;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataOraUltimoLogin;
	//@OneToMany
	//private List<Email> emails;
	
	
	public Utente() {
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
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	//public ArrayList<Email> getEmails() {
	//	return emails;
	//}
	//public void setEmails(ArrayList<Email> emails) {
	//	this.emails = emails;
	//}
	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}
	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}


	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", dataDiNascita="
				+ dataDiNascita + ", dataRegistrazione=" + dataRegistrazione + ", dataOraUltimoLogin="
				+ dataOraUltimoLogin + "]";
	}


	public Date getDataOraUltimoLogin() {
		return dataOraUltimoLogin;
	}


	public void setDataOraUltimoLogin(Date dataOraUltimoLogin) {
		this.dataOraUltimoLogin = dataOraUltimoLogin;
	}


	public String getTipoUtente() {
		return tipoUtente;
	}


	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}


	public Integer getContatoreAccessiSbagliati() {
		return contatoreAccessiSbagliati;
	}


	public void setContatoreAccessiSbagliati(Integer contatoreAccessiSbagliati) {
		this.contatoreAccessiSbagliati = contatoreAccessiSbagliati;
	}

	
}
