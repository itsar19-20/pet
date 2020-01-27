package interfaces;

import java.util.List;

import model.Segnalazione;
import model.Utente;

public interface AdminInterface {
	
	//UTENTI
	//abstract void riattivazioneUtentiBloccatiLogin(String username);
	abstract void bloccaSbloccaUtenti(String username);
	abstract List<Utente> gestioneUtenti();
	//abstract List<Utente> visualizzaUtenti(String username);
	//abstract List<Utente> visualizzaDettagliUtente(String username);	
	abstract void eliminaUtenti(String username);

	abstract List<Segnalazione> visualizzaSegnalazioni();
	
	abstract List<Segnalazione> visualizzaSegnalazioniSingoloUtente(String username);
	
	abstract void rimuoviAnnuncio(String id_annuncio);
	abstract void rimuoviValutazione(String id_valutazione);
	abstract String visualizzaDescrizioneEvento(String id_evento);
	
	
	//abstract void logout();
}
