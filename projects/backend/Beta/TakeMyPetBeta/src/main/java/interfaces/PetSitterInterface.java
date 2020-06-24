package interfaces;

import java.util.List;

import model.Annuncio;
import model.PetSitter;
import model.Preferiti;

public interface PetSitterInterface extends UtenteAppInterface {
	abstract  void partecipaAnnuncio(Integer idAnnuncio, String usernamePetSitter);
	abstract List<Annuncio> listaAnnunciPetSitter();
	abstract String creaPreferito(String usernamePetSitter, int idAnnuncio);
	abstract List<Preferiti> visualizzaPreferitiPetSitter(String usernamePetSitter);
	abstract List<PetSitter> visualizzaPetSitterPerAnnuncio(int id_annuncio);
}
