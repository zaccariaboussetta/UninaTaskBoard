package dao;

import java.util.ArrayList;

import entities.PreparazioneEsami;
import entities.Progetto;
import entities.SviluppoApplicativi;
import entities.Utente;

public interface ProgettoDAO {
	
	public ArrayList<Progetto> getProjectsByUtente(Utente utente);
	
	public boolean inserisiciNuovoProgettoGenerico(Progetto project);
	public boolean inserisiciNuovoProgettoSviluppo(SviluppoApplicativi project);
	public boolean inserisiciNuovoProgettoEsame(PreparazioneEsami project);
	public boolean inserisiciNuovoProgettoSviluppoEsame(Progetto project);
}
