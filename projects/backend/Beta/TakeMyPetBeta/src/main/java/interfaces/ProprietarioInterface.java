package interfaces;

import java.util.Date;
import java.util.List;

import model.Animale;
import model.Annuncio;
import model.Preferiti;
import model.Proprietario;

public interface ProprietarioInterface extends UtenteAppInterface {
	
	abstract List<Animale> visualizzaAnimali(String usernameProprietario);
	abstract void eliminaAnimale(Integer idAnimale);
	abstract String aggiungiAnimale(String usernameProprietario, Date dataDiNascita, String dettagli,Integer eta,String nome,String razza,String tipo);
	abstract void aggiungiAnnuncio(String nomeAnnuncio, String usernameProprietario,String descrizione,String longitudine, String latitudine, List<Animale> listaAnimali, Date dataAnnuncio, Date dataCreazioneAnnuncio);
	abstract void rimuoviAnnuncio(Integer idAnnuncio);
	abstract List<Annuncio> listaAnnunciProprietario(String usernameProprietario);
	abstract void creaPreferitoProprietario(String usernameProprietario, Integer idAnnuncio, String usernamePetSitter);
	abstract List<Preferiti> visualizzaPreferitiProprietario (String usernameProprietario);
}
