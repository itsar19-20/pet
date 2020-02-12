package interfaces;

import java.util.List;

import model.Animale;
import model.Proprietario;

public interface ProprietarioInterface extends UtenteAppInterface {
	
	abstract List<Animale> visualizzaAnimale(Proprietario utente);
	abstract void eliminaAnimale(Integer idAnimale);

}
