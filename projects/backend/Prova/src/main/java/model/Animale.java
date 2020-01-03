package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	

	
}
