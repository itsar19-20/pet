package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Proprietario extends UtenteApp{
	
	@OneToMany
	@JsonIgnore
	private List<Animale> animali;

	
	public List<Animale> getAnimali() {
		return animali;
	}
	
	
	public void setAnimali(List<Animale> animali) {
		this.animali = animali;
	}
	
	
	

}
