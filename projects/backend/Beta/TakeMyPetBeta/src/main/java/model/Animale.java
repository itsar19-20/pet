package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@NamedQueries(  
	    {  	 @NamedQuery(  
		        name = "cercaAnimaliPerProprietario",  
		        query = "select c from Animale c where c.proprietario= :username" 
		        ) 
	      
 } 
	
	)	    		 

@Entity
public class Animale {
	        
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_animale;
	private String tipo;
	private String razza;
	private String nome;
	private int eta;
	private String dettagli;
	private Date dataDiNascita;
	@OneToOne
	private Immagine immagine;
	
	@ManyToOne
	private Proprietario proprietario;

	
	
	
	@Override
	public String toString() {
		return "Animale [id_animale=" + id_animale + ", tipo=" + tipo + ", razza=" + razza + ", nome=" + nome + ", eta="
				+ eta + ", dettagli=" + dettagli + ", dataDiNascita=" + dataDiNascita + ", immagine=" + immagine
				+ ", proprietario=" + proprietario + "]";
	}
	public int getId_animale() {
		return id_animale;
	}
	public void setId_animale(int id_animale) {
		this.id_animale = id_animale;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRazza() {
		return razza;
	}
	public void setRazza(String razza) {
		this.razza = razza;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getEta() {
		return eta;
	}
	public void setEta(int eta) {
		this.eta = eta;
	}
	public String getDettagli() {
		return dettagli;
	}
	public void setDettagli(String dettagli) {
		this.dettagli = dettagli;
	}
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public Immagine getImmagine() {
		return immagine;
	}
	public void setImmagine(Immagine immagine) {
		this.immagine = immagine;
	}
	public Proprietario getProprietario() {
		return proprietario;
	}
	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
	

	
}
