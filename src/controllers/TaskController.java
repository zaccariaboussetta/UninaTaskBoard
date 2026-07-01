package controllers;

import java.util.ArrayList;
import dao.TaskDAO;
import entities.Attivita;
import entities.Progetto;

public class TaskController {

	private TaskDAO taskDAO;
	
	public TaskController(TaskDAO t) {
		this.taskDAO = t;
	}
	
	// Metodo per richiedere le attività dal DAO
	public ArrayList<Attivita> getTasksByProgetto(Progetto progetto) {
		return taskDAO.getTasksByProgetto(progetto);
	}
	
}
