package interfaces;

import java.util.Date;
import java.util.List;

import model.Annuncio;
import model.Evento;
import model.UtenteApp;

public interface UtenteAppInterface {
	

	abstract void inviaSegnalazione(UtenteApp segnalato, UtenteApp segnalatore, String descrizione, Annuncio annuncio, Evento evento); //
	abstract void inviaValutazione(UtenteApp  valutato, UtenteApp valutatore,String commento,float numeroOrme); //
	//abstract void usaChat();
	abstract UtenteApp visualizzaProfilo(String username); //
	//abstract void logout(String username);
	//abstract void modificaProfilo(String username);
	//abstract UtenteApp cambiaTipoProfilo(String username);
	//abstract void refresh();
    abstract List<Evento> visualizzaEventiUtente(UtenteApp utente); //
	abstract List<Evento> visualizzaTuttiEventi(); //
	abstract void nuovoEvento(String nomeEvento,  Date dataEvento, String descrizione, String latitudine, String longitudine, UtenteApp organizzatore);//
	//abstract void partecipaEvento(Evento evento, UtenteApp partecipante); //

	

}
