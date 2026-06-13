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
	
	
	public void registraUtente(String nome, String cognome,String email,String matricola,String password) throws RegistrationException {

		Utente newUtente = new Utente(nome, cognome, matricola, email, password);
		if(!utenteDAO.insertUtente(newUtente)) {
			throw new RegistrationException("Errore nella regitrazione.");
		}
			
	}
	
	public void verifyValidPassword(String password,String passwordDiConferma) throws PasswordException {
		 if(!password.equals(passwordDiConferma)) {
			 throw new PasswordException("Passwords non combaciano.");
		 }
	}
	
	public void verifyValidEMail(String email) throws EmailException {
		if(!email.endsWith("@studenti.unina.it")) {
			throw new EmailException("Non hai fornito una email istiuzionale.");
		}
	}
	
	public void verifyValidMatricola(String matricola) throws MatricolaException {
		if(!matricola.startsWith("N8600")) {
			throw new MatricolaException("Matricola non conforme.");
		}
	}
	
}
