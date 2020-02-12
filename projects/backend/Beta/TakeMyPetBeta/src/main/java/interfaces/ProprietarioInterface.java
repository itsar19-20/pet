package interfaces;

import java.util.Date;
import java.util.List;

import model.Animale;
import model.Proprietario;

public interface ProprietarioInterface extends UtenteAppInterface {
	
	abstract List<Animale> visualizzaAnimale(Proprietario utente);
	abstract void eliminaAnimale(Integer idAnimale);
	public abstract String aggiungiAnimale( String dataDiNascita, String dettagli,Integer eta,String nome,String razza,String tipo);

}
