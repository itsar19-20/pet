package interfaces;

import java.util.List;

import model.Evento;
import model.Segnalazione;
import model.UtenteApp;
import model.Valutazione;

public interface UtenteAppInterface {
	
	abstract List<Evento> visualizzaEventi(UtenteApp utente);
	abstract void inviaSegnalazione(Segnalazione segnalazione);
	abstract void inviaValutazione(Valutazione valutazione);
	abstract void usaChat();
	abstract UtenteApp visualizzaProprioProfilo(String username);
	abstract UtenteApp visualizzaProfilo(String username);
	abstract void logout(String username);
	abstract void modificaProfilo(String username);
	abstract UtenteApp cambiaTipoProfilo(String username);
	abstract void refresh();
	abstract void aggiungiEvento(UtenteApp organizzatore, List<UtenteApp> partecipanti, );
	

}
