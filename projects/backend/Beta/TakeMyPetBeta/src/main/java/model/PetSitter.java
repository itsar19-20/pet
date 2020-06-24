
package model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



@NamedQueries({
	   
    
    @NamedQuery(name="petsitter.findByPetSitter",
    query="SELECT c FROM PetSitter c WHERE c.username= :name"),
    
    @NamedQuery(name="petsitter.findByPreferiti",
    query="SELECT c.preferitoDelPetSitter FROM Preferiti c WHERE c.annuncioPreferito.id_annuncio=:id"),
    
    
})
@Entity
public class PetSitter extends UtenteApp {
	
	
	
	

}
