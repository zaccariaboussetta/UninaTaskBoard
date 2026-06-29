package controllers;

import entities.Progetto;
import entities.Utente;

public class SessionController {

	private Utente utenteLoggato = null;
	private Progetto progettoUtente = null;
	private static SessionController instance = null;
	
	private SessionController() {};
	
	public static SessionController getInstance() {
		if(instance == null) {
			
			instance = new SessionController();
			
		}
		
		return instance;
	}
	
	public void startSession(Utente utente) {
		this.utenteLoggato = utente;
	}
	
	public void setCorrenteProgetto(Progetto progetto) {
		this.progettoUtente = progetto;
	}
	
	public Utente getUtenteLoggato() {
		return this.utenteLoggato;
	}
	
	public void closeSession() {
		this.utenteLoggato = null;
	}
	
	public boolean isUtenteLoggato() {
		return utenteLoggato != null;
	}
}

