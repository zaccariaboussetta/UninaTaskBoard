package dao;

import java.util.ArrayList;

import entities.PreparazioneEsami;
import entities.Progetto;
import entities.SviluppoApplicativi;
import entities.Utente;

public interface ProgettoDAO {
	
	public ArrayList<Progetto> getProjectsByUtente(Utente utente);
	
	public boolean inserisiciNuovoProgetto(Progetto project);
	public boolean inserisiciNuovoProgetto(SviluppoApplicativi project);
	public boolean inserisiciNuovoProgetto(PreparazioneEsami project);
	public boolean inserisiciNuovoProgetto(PreparazioneEsami progettoEsame, SviluppoApplicativi progettoSviluppo);
}
