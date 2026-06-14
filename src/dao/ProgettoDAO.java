package dao;

import java.util.ArrayList;

import entities.Progetto;
import entities.Utente;

public interface ProgettoDAO {
	
	public ArrayList<Progetto> getProjectsByUtente(Utente utente);
	
	public void insertProject(Progetto project);
	
}
