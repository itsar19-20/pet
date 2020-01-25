package interfaces;

import java.util.List;

import model.Segnalazione;

public interface AdminInterface {
	
	abstract List<Segnalazione> visualizzaSegnalazioni();
	abstract void bloccaSbloccaUtenti(String username);
	abstract void eliminaUtenti(String username);
	abstract void rimuoviAnnuncio(String id_annuncio);
	abstract void rimuoviValutazione(String id_valutazione);
	abstract String visualizzaDescrizioneEvento(String id_evento);
}
