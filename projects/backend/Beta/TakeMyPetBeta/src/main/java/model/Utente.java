package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@NamedQueries({
    @NamedQuery(name="utente.findAll",
                query="SELECT c FROM Utente c WHERE NOT c.tipoUtente = 'admin'"),
    @NamedQuery(name="utente.findByUsername",
                query="SELECT c FROM Utente c WHERE c.username = :name"),
    @NamedQuery(name="utente.statRegistrazioneDay",
    			query="SELECT c.dataRegistrazione , COUNT(c.username)AS dataReg FROM Utente c WHERE NOT c.tipoUtente = 'admin' GROUP BY c.dataRegistrazione ORDER BY c.dataRegistrazione"),
})
public class Utente {
	
	private String tipoUtente;
	private Integer contatoreAccessiSbagliati = 0;
	
	
	private String nome;
	private String cognome;
	@Id
	private String username;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String codiceSblocco;
	
	


	@Temporal(TemporalType.DATE)
	private Date dataDiNascita;
	@Temporal(TemporalType.DATE)
	private Date dataRegistrazione;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataOraUltimoLogin;
	@OneToMany
	private List<Email> emails;
	
	
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
		return "Utente [tipoUtente=" + tipoUtente + ", contatoreAccessiSbagliati=" + contatoreAccessiSbagliati
				+ ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password=" + password
				+ ", codiceSblocco=" + codiceSblocco + ", dataDiNascita=" + dataDiNascita + ", dataRegistrazione="
				+ dataRegistrazione + ", dataOraUltimoLogin=" + dataOraUltimoLogin + "]";
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

	public String getCodiceSblocco() {
		return codiceSblocco;
	}


	public void setCodiceSblocco(String codiceSblocco) {
		this.codiceSblocco = codiceSblocco;
	}
	
}
