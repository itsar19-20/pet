package model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Immagine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Immagine;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] byteArray;
	
	
	
	
	
	public Integer getId_Immagine() {
		return id_Immagine;
	}
	public void setId_Immagine(Integer id_Immagine) {
		this.id_Immagine = id_Immagine;
	}
	public byte[] getByteArray() {
		return byteArray;
	}
	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

}
