package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	   
    @NamedQuery(name="annuncio.findByProprietario",
                query="SELECT c FROM Annuncio c WHERE c.proprietarioAnnuncio.username like :name"),
    @NamedQuery(name="annuncio.findAll",
    			query="SELECT c FROM Annuncio c WHERE NOT c.terminato = true "),
})
public class Annuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_annuncio;
	@OneToOne
	private Proprietario proprietarioAnnuncio;
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PetSitter> petSittersAnnuncio;
	@OneToMany
	@JsonIgnore
	private List<Animale> animaliAnnuncio;
	private String descrizione;
	private String latitudine;
	private String longitudine;
	private Date dataCreazioneAnnuncio;
	private Date dataAnnuncio;
	private String nomeAnnuncio;
	private String urlImmagineAnnuncio;
	
	
	private boolean terminato;
	
	
	
	public int getId_annuncio() {
		return id_annuncio;
	}
	public void setId_annuncio(int id_annuncio) {
		this.id_annuncio = id_annuncio;
	}
	public Proprietario getProprietarioAnnuncio() {
		return proprietarioAnnuncio;
	}
	public void setProprietarioAnnuncio(Proprietario proprietarioAnnuncio) {
		this.proprietarioAnnuncio = proprietarioAnnuncio;
	}
	public List<PetSitter> getPetSittersAnnuncio() {
		return petSittersAnnuncio;
	}
	public void setPetSittersAnnuncio(List<PetSitter> petSittersAnnuncio) {
		this.petSittersAnnuncio = petSittersAnnuncio;
	}
	public List<Animale> getAnimaliAnnuncio() {
		return animaliAnnuncio;
	}
	public void setAnimaliAnnuncio(List<Animale> animaliAnnuncio) {
		this.animaliAnnuncio = animaliAnnuncio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}
	public String getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}
	public Date getDataCreazioneAnnuncio() {
		return dataCreazioneAnnuncio;
	}
	public void setDataCreazioneAnnuncio(Date dataCreazioneAnnuncio) {
		this.dataCreazioneAnnuncio = dataCreazioneAnnuncio;
	}
	public Date getDataAnnuncio() {
		return dataAnnuncio;
	}
	public void setDataAnnuncio(Date dataAnnuncio) {
		this.dataAnnuncio = dataAnnuncio;
	}
	public boolean isTerminato() {
		return terminato;
	}
	public void setTerminato(boolean terminato) {
		this.terminato = terminato;
	}
	public String getNomeAnnuncio() {
		return nomeAnnuncio;
	}
	public void setNomeAnnuncio(String nomeAnnuncio) {
		this.nomeAnnuncio = nomeAnnuncio;
	}
	
	public String getUrlImmagineAnnuncio() {
		return urlImmagineAnnuncio;
	}
	public void setUrlImmagineAnnuncio(String urlImmagineAnnuncio) {
		this.urlImmagineAnnuncio = urlImmagineAnnuncio;
	}
	
	@Override
	public String toString() {
		return "Annuncio [id_annuncio=" + id_annuncio + ", proprietarioAnnuncio=" + proprietarioAnnuncio
				+ ", petSittersAnnuncio=" + petSittersAnnuncio + ", animaliAnnuncio=" + animaliAnnuncio
				+ ", descrizione=" + descrizione + ", latitudine=" + latitudine + ", longitudine=" + longitudine
				+ ", dataCreazioneAnnuncio=" + dataCreazioneAnnuncio + ", dataAnnuncio=" + dataAnnuncio
				+ ", nomeAnnuncio=" + nomeAnnuncio + ", urlImmagineAnnuncio=" + urlImmagineAnnuncio + ", terminato="
				+ terminato + "]";
	}
	

}
