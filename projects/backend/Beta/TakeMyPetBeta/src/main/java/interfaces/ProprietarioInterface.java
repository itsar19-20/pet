package interfaces;

import java.util.Date;
import java.util.List;

import model.Animale;
import model.Annuncio;
import model.Proprietario;

public interface ProprietarioInterface extends UtenteAppInterface {
	
	abstract List<Animale> visualizzaAnimale(Proprietario utente);
	abstract void eliminaAnimale(Integer idAnimale);
	public abstract String aggiungiAnimale( Date dataDiNascita, String dettagli,Integer eta,String nome,String razza,String tipo);
	abstract void aggiungiAnnuncio(String usernameProprietario,String descrizione,String longitudine, String latitudine, List<Animale> listaAnimali);
	abstract void rimuoviAnnuncio(Integer idAnnuncio);
	abstract List<Annuncio> listaAnnunciProprietario(String usernameProprietario);
}
