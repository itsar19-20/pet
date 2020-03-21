package interfaces;

import java.util.List;

import model.Annuncio;
import model.Preferiti;

public interface PetSitterInterface extends UtenteAppInterface {
	abstract  void partecipaAnnuncio(Integer idAnnuncio, String usernamePetSitter);
	abstract List<Annuncio> listaAnnunciPetSitter();
	abstract void creaPreferito(String usernamePetSitter, Integer idAnnuncio);
	abstract List<Preferiti> visualizzaPreferitiPetSitter(String usernamePetSitter);
}
