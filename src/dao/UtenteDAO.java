package dao;

import entities.Utente;

public interface UtenteDAO {

	//CRUD: CREATE, READ, UPDATE, DELETE
	
	boolean insertUtente(Utente newUtente);
	Utente getUtenteByEmail(String email);
}
