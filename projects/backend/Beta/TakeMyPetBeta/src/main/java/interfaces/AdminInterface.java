package interfaces;

import java.util.List;

import model.Segnalazione;
import model.Utente;
import model.UtenteApp;

public interface AdminInterface {

	abstract List<Segnalazione> visualizzaSegnalazioni();
	
	abstract void rimuoviSegnalazione(Integer idSegnalazione);
	
	abstract List<Segnalazione> segnalazioniSingoloUtente(String username,boolean controlloTipo);
	
	abstract void bloccaUtente(String username);
	
	abstract void riattivaUtente(String username);
	
	abstract void eliminaUtente(String username);
	
	abstract List<Utente> visualizzaUtenti();
	
	abstract String visualizzaTestoEvento(Integer idEvento);
	
	abstract void eliminaEvento(Integer idEvento);
	
	abstract String visualizzaTestoAnnuncio(Integer idAnnuncio);
	
	abstract void eliminaAnnuncio(Integer idAnnuncio);
	
	abstract String visualizzaTestoValutazione(Integer idValutazione);
	
	abstract void eliminaValutazione(Integer idValutazione);
	
	abstract List<Object> statUtentiRegistratiDay();
	
}
