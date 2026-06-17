package dao;

import java.util.ArrayList;

import entities.Progetto;
import entities.Utente;

public interface ProgettoDAO {
	
	public ArrayList<Progetto> getProjectsByUtente(Utente utente);
	
	public boolean inserisiciNuovoProgettoGenerico(Progetto project);
	public boolean inserisiciNuovoProgettoSviluppo(Progetto project);
	public boolean inserisiciNuovoProgettoEsame(Progetto project);
	public boolean inserisiciNuovoProgettoSviluppoEsame(Progetto project);
}