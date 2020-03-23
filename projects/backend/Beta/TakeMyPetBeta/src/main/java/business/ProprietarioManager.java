package business;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.ProprietarioInterface;
import model.Animale;
import model.Annuncio;
import model.Evento;
import model.PetSitter;
import model.Preferiti;
import model.Proprietario;
import model.Segnalazione;
import model.UtenteApp;
import model.Valutazione;
import utils.JPAUtil;

public class ProprietarioManager extends UtenteAppManager implements ProprietarioInterface {

	public void modificaProfilo(String username) {
		// TODO Auto-generated method stub

	}

	public UtenteApp cambiaTipoProfilo(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// Animali
	public List<Animale> visualizzaAnimali(String usernameProrietario) {
		List<Animale> _return = new ArrayList<Animale>();

		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		_return = em.createNamedQuery("cercaAnimaliPerProprietario", Animale.class)
				.setParameter("username", usernameProrietario).getResultList();

		em.close();
		return _return;
	}

	@Override
	public void eliminaAnimale(Integer idAnimale) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		em.getTransaction().begin();
		em.remove(em.find(Animale.class, idAnimale));
		em.getTransaction().commit();
	}

	@Override
	public String aggiungiAnimale(String usernameProprietario, Date dataDiNascita, String dettagli, Integer eta, String nome, String razza,
			String tipo) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Proprietario proprietario = new Proprietario();
		
		proprietario = em.find(Proprietario.class, usernameProprietario);
		Animale a = new Animale();
		
		a.setDataDiNascita(dataDiNascita);
		a.setDettagli(dettagli);
		a.setEta(eta);
		a.setNome(nome);
		a.setRazza(razza);
		a.setTipo(tipo);
		a.setProprietario(proprietario);

		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		em.close();
		return null;
	}

	public void aggiungiAnnuncio(String nomeAnnuncio, String usernameProprietario, String descrizione, String longitudine, String latitudine,
			List<Animale> listaAnimali, Date dataAnnuncio, Date dataCreazioneAnnuncio) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		Annuncio annuncio = new Annuncio();
		Proprietario proprietario = new Proprietario();

		proprietario = em.find(Proprietario.class, usernameProprietario);

		annuncio.setNomeAnnuncio(nomeAnnuncio);
		annuncio.setDataAnnuncio(dataAnnuncio);
		annuncio.setDataCreazioneAnnuncio(dataCreazioneAnnuncio);
		annuncio.setTerminato(false);
		annuncio.setProprietarioAnnuncio(proprietario);
		annuncio.setDescrizione(descrizione);
		annuncio.setAnimaliAnnuncio(listaAnimali);
		annuncio.setLongitudine(longitudine);
		annuncio.setLongitudine(longitudine);

		em.getTransaction().begin();
		em.persist(annuncio);
		em.getTransaction().commit();
		em.close();

	}

	public void rimuoviAnnuncio(Integer idAnnuncio) {

		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		em.getTransaction().begin();
		em.remove(em.find(Annuncio.class, idAnnuncio));
		em.getTransaction().commit();
		em.close();

	}

	public List<Annuncio> listaAnnunciProprietario(String usernameProprietario) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

		List<Annuncio> _return = new ArrayList<Annuncio>();
		_return = em.createNamedQuery("annuncio.findByProprietario").setParameter("name", usernameProprietario)
				.getResultList();
		return _return;

	}

	public void creaPreferitoProprietario(String usernameProprietario, Integer idAnnuncio, String usernamePetSitter) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Preferiti preferito = new Preferiti();
		PetSitter petSitter = new PetSitter();
		Annuncio annuncio = new Annuncio();
		Proprietario proprietario = new Proprietario();
		
		proprietario = em.find(Proprietario.class, usernameProprietario);
		petSitter = em.find(PetSitter.class, usernamePetSitter);
		annuncio = em.find(Annuncio.class, idAnnuncio);
		
		preferito.setAnnuncioPreferito(annuncio);
		preferito.setPreferitoDelProprietario(proprietario);
		preferito.setPetSitterPreferitoDelProprietario(petSitter);
		em.getTransaction().begin();
		em.persist(preferito);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Preferiti> visualizzaPreferitiProprietario (String usernameProprietario) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		List<Preferiti> _return = new ArrayList<Preferiti>();
		_return = em.createNamedQuery("preferiti.findByProprietario").setParameter("name", usernameProprietario).getResultList();
		em.close();
		return _return;
	}
	
}
