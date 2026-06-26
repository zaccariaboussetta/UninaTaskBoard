package controllers;

import dao.UtenteDAO;
import entities.Utente;
import exceptions.EmailException;
import exceptions.MatricolaException;
import exceptions.PasswordException;
import exceptions.RegistrationException;

public class RegistrazioneUtenteController {
	
	private UtenteDAO utenteDAO;
	
	public RegistrazioneUtenteController(UtenteDAO utenteDAO) {this.utenteDAO = utenteDAO;}
	
	
	public void registraUtente(String nome, String cognome,String email,String matricola,String password, String passwordConferma) throws Exception {

		this.verifyValidEMail(email);
		this.verifyValidMatricola(matricola);
		this.verifyValidPassword(password, passwordConferma);
		
		Utente newUtente = new Utente(nome, cognome, matricola, email, password);
		utenteDAO.insertUtente(newUtente);
		SessionController.getInstance().startSession(newUtente);
	}
	
	public void verifyValidPassword(String password,String passwordDiConferma) throws Exception {
		if (password.isBlank() || passwordDiConferma.isBlank()) throw new RegistrationException(); 
		if(!password.equals(passwordDiConferma)) throw new Exception("Passwords non combaciano.");
	}
	
	public void verifyValidEMail(String email) throws Exception {
		if(email.isBlank()) throw new RegistrationException();
		if(!email.endsWith("@studenti.unina.it")) throw new Exception("Non hai fornito una email istiuzionale.");
	}
	
	public void verifyValidMatricola(String matricola) throws Exception {
		if(matricola.isBlank()) throw new RegistrationException();
		if(!matricola.startsWith("N8600") || matricola.length()!= 9) throw new Exception("Matricola non conforme.");
	}
	
}

