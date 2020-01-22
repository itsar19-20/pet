package interfaces;

import java.util.List;

import model.Evento;
import model.UtenteApp;

public interface UtenteAppInterface {
	
	abstract List<Evento> visualizzaEventiUtente(UtenteApp utente);
	abstract List<Evento> visualizzaTuttiEventi();

}
