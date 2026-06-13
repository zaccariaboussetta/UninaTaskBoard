package controllers;

import dao.UtenteDAO;
import dao.postgres.UtenteDAOPostgres;
import entities.Utente;

public class AuthController {
	
	private UtenteDAO utenteDAO;
	
	public AuthController(UtenteDAO utente) {this.utenteDAO = utente;}
	
	public boolean authenticationLogin(String email, String password) {
		
		Utente utente = utenteDAO.getUtenteByEmail(email);
		
		if(utente != null && utente.getPassword().equals(password)) {
			
			SessionController.getInstance().startSession(utente);
			return true;
			
		}
		
		return false;
		
	}
}
