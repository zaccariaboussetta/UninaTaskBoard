package controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.ProgettoDAO;
import dao.postgres.ProgettoDAOPostgres;
import entities.Progetto;
import entities.Utente;

public class ProgettoController {
	
	private ProgettoDAO projectDAO;
	
	public ProgettoController(ProgettoDAO projectDAO) {
		
		this.projectDAO = projectDAO;
		
		}
	
	public ArrayList<Progetto> getProgettiUtente(){
		
		ArrayList<Progetto> listProgetti = new ArrayList();
		listProgetti = projectDAO.getProjectsByUtente(SessionController.getInstance().getUtenteLoggato());
		
		
		if(!listProgetti.isEmpty()) {
			
			return listProgetti;
			
		}
		
		return null;
	}
	
	public boolean aggiungiNuovoProgettoGenerico(String nome, String descrizione, LocalDate dataConsegna, boolean isProgettoGruppo){
		
		Utente creatoreProgetto = SessionController.getInstance().getUtenteLoggato();
		Progetto newProgetto = new Progetto(nome, descrizione, dataConsegna, isProgettoGruppo, creatoreProgetto);
		
		ProgettoDAO progettoDAO = new ProgettoDAOPostgres();
		
		progettoDAO.inserisiciNuovoProgettoGenerico(newProgetto);
		
		return true;
	}
	
	public boolean aggiungiNuovoProgettoSviluppo() {
		
		return true;
	}

	public boolean aggiungiNuovoProgettoEsame() {

		return true;
	}
	
	public boolean aggiungiNuovoProgettoEsameSviluppo() {

		return true;
	}
	
	
}
