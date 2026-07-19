package dao;

import java.util.ArrayList;

import entities.Invito;
import entities.Utente;

public interface InvitoDAO {

	public ArrayList<Invito> getinvitiByUtente(Utente utente);
	
}
