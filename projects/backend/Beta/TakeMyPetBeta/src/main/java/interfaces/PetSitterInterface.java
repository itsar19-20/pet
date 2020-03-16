package interfaces;

import java.util.List;

import model.Annuncio;

public interface PetSitterInterface extends UtenteAppInterface {
	abstract  void partecipaAnnuncio(Integer idAnnuncio, String usernamePetSitter);
	abstract List<Annuncio> listaAnnunciPetSitter();
}
