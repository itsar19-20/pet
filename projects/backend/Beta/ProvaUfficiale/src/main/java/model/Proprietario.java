package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Proprietario extends UtenteApp{
	
	@OneToMany
	private List<Animale> animali;

	public List<Animale> getAnimali() {
		return animali;
	}

	public void setAnimali(List<Animale> animali) {
		this.animali = animali;
	}
	
	
	

}
