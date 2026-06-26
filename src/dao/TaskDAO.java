package dao;

import java.util.ArrayList;

import entities.Attivita;
import entities.Progetto;

public interface TaskDAO {

	public ArrayList<Attivita> getTasksByProgetto(Progetto progetto);
	
}
