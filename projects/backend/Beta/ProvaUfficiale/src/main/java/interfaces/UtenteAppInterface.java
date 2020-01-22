package interfaces;

import java.util.Date;
import java.util.List;

import model.Evento;
import model.UtenteApp;

public interface UtenteAppInterface {
	
	abstract List<Evento> visualizzaEventiUtente(UtenteApp utente);
	abstract List<Evento> visualizzaTuttiEventi();

	public abstract void nuovoEvento( String nomeEvento,  Date dataDiNascita, Date dataDiRegistrazione, String descrizione, String latitudine, String longitudine);
	

}
