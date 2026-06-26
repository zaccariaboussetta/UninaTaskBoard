package controllers;

import dao.UtenteDAO;
import entities.Utente;

public class AuthController {
	
	private UtenteDAO utenteDAO;
	
	public AuthController(UtenteDAO utente) {this.utenteDAO = utente;}
	
	public void authenticationLogin (String email, String password) throws Exception{
		
		if (email.isBlank() || password.isBlank()) throw new Exception("Compilare tutti i campi");
		
		Utente utente = utenteDAO.getUtenteByEmail(email);
		
		if(utente == null) throw new Exception("Utente non registrato");
		if(!utente.getPassword().equals(password)) throw new Exception("Password sbagliata");
		
		SessionController.getInstance().startSession(utente);
		
	}
}

