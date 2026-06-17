package controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.ProgettoDAO;
import dao.postgres.ProgettoDAOPostgres;
import entities.PreparazioneEsami;
import entities.Progetto;
import entities.SviluppoApplicativi;
import entities.Utente;

public class ProgettoController {
	
	private ProgettoDAO progettoDAO;
	
	public ProgettoController() {
		
		this.progettoDAO = new ProgettoDAOPostgres();
		
		}
	
	public ArrayList<Progetto> getProgettiUtente(){
		
		ArrayList<Progetto> listProgetti = new ArrayList();
		listProgetti = progettoDAO.getProjectsByUtente(SessionController.getInstance().getUtenteLoggato());
		
		
		if(!listProgetti.isEmpty()) {
			
			return listProgetti;
			
		}
		
		return null;
	}
	
	public boolean aggiungiNuovoProgettoGenerico(String nome, String descrizione, LocalDate dataConsegna, boolean isProgettoGruppo) throws Exception{
		
		if(nome.isBlank() || descrizione.isBlank()) {
			throw new Exception("Campi vuoti: Nome e Descrizione.");
		}
		
		if(dataConsegna.isBefore(LocalDate.now())) {
			throw new Exception("Data di consegna non valida.");
		}
		
		Utente creatoreProgetto = SessionController.getInstance().getUtenteLoggato();
		Progetto newProgetto = new Progetto(nome, descrizione, dataConsegna, isProgettoGruppo, creatoreProgetto);
		
		return progettoDAO.inserisiciNuovoProgetto(newProgetto);
		
	}
	
	
	public boolean aggiungiNuovoProgettoSviluppo(String nome, String descrizione, LocalDate dataConsegna, boolean isProgettoGruppo, String repoURL, String techStack, String versione) throws Exception {
		
	    if(nome.isBlank() || descrizione.isBlank()) {
	        throw new Exception("Campi vuoti: Nome e Descrizione.");
	    }
	    
	    if(dataConsegna.isBefore(LocalDate.now())) {
	        throw new Exception("Data di consegna non valida.");
	    }
	    
	    String repoClean = repoURL.isBlank() ? null : repoURL.trim();
	    String techClean = techStack.isBlank() ? null : techStack.trim();
	    String versClean = versione.isBlank() ? null : versione.trim();
	    
	    Utente creatoreProgetto = SessionController.getInstance().getUtenteLoggato();
	    
	    SviluppoApplicativi newProgetto = new SviluppoApplicativi(
	        nome, descrizione, dataConsegna, isProgettoGruppo, 
	        creatoreProgetto, repoClean, techClean, versClean
	    );
	    
	    return progettoDAO.inserisiciNuovoProgetto(newProgetto);
	}

	
	public boolean aggiungiNuovoProgettoEsame (String nome, String descrizione, LocalDate dataConsegna, boolean isProgettoGruppo, String codiceEsame, String nomeEsame, String cfu, String docente, LocalDate dataAppello) throws Exception {

		if(nome.isBlank() || descrizione.isBlank() || codiceEsame.isBlank() || docente.isBlank() || cfu.isBlank() || nomeEsame.isBlank()) {
			throw new Exception("Compilare tutti i campi.");
		}
		
		if(dataConsegna.isBefore(LocalDate.now())) {
			throw new Exception("Data di consegna non valida.");
		}
		
		
		if(dataAppello != null && dataAppello.isBefore(LocalDate.now())) {
			throw new Exception("Data appello non valida.");
		}
		
		Utente creatoreProgetto = SessionController.getInstance().getUtenteLoggato();
		PreparazioneEsami newProgetto = new PreparazioneEsami(nome, descrizione, dataConsegna, isProgettoGruppo, creatoreProgetto, codiceEsame, nomeEsame, Integer.parseInt(cfu), docente, dataAppello);
		
		return progettoDAO.inserisiciNuovoProgetto(newProgetto);
	}
	
	
	public boolean aggiungiNuovoProgettoEsameSviluppo(String nome, String descrizione, LocalDate dataConsegna, boolean isProgettoGruppo, String codiceEsame, String nomeEsame, String cfu, String docente, LocalDate dataAppello, String repoURL, String techStack, String versione) throws Exception {

		if(nome.isBlank() || descrizione.isBlank() || codiceEsame.isBlank() || docente.isBlank() || cfu.isBlank() || nomeEsame.isBlank()) {
			throw new Exception("Compilare tutti i campi.");
		}
		
		if(dataConsegna.isBefore(LocalDate.now())) {
			throw new Exception("Data di consegna non valida.");
		}
		
		if(dataAppello != null && dataAppello.isBefore(LocalDate.now())) {
			throw new Exception("Data appello non valida.");
		}
		
		String repoClean = repoURL.isBlank() ? null : repoURL.trim();
		String techClean = techStack.isBlank() ? null : techStack.trim();
		String versClean = versione.isBlank() ? null : versione.trim();
		
		Utente creatoreProgetto = SessionController.getInstance().getUtenteLoggato();
		
		PreparazioneEsami progettoEsame = new PreparazioneEsami(nome, descrizione, dataConsegna, isProgettoGruppo, creatoreProgetto, codiceEsame, nomeEsame, Integer.parseInt(cfu), docente, dataAppello);
		
		SviluppoApplicativi progettoSviluppo = new SviluppoApplicativi(nome, descrizione, dataConsegna, isProgettoGruppo, creatoreProgetto, repoClean, techClean, versClean);
		
		return progettoDAO.inserisiciNuovoProgetto(progettoEsame, progettoSviluppo);
	}
	
	
}
