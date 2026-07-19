package dao;

import entities.Utente;

public interface UtenteDAO {

	
	
	boolean insertUtente(Utente newUtente);
	Utente getUtenteByEmail(String email);
}
