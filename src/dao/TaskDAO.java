package dao;

import java.util.ArrayList;

import entities.Attivita;
import entities.Membro;
import entities.Progetto;

public interface TaskDAO {

	public ArrayList<Attivita> getTasksByProgetto(Progetto progetto);
	public ArrayList<Attivita> getTasksAssignedTo(Membro membro);
	public void updateTaskStatus(Progetto progetto, Attivita task, String status);
	public void assignTaskTo(Membro membro, Progetto progetto, Attivita task); 
	public int inserisciNuovaAttivita(Attivita task, Progetto progetto);
}

