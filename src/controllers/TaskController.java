package controllers;

import java.util.ArrayList;
import dao.TaskDAO;
import entities.Attivita;
import entities.Membro;
import entities.Progetto;

public class TaskController {

	private TaskDAO taskDAO;
	
	public TaskController(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}
	
	public ArrayList<Attivita> getTasksByProgetto(Progetto progetto) {
		return taskDAO.getTasksByProgetto(progetto);
	}
	
	public ArrayList<Attivita> getTasksAssignedTo(Membro membro) {
		return taskDAO.getTasksAssignedTo(membro);
	}
	
	public void updateTaskStatus(Progetto progetto, Attivita task, String status) {
		if (status.equals("Todo") || status.equals("In_progress") || status.equals("Done")) {
			taskDAO.updateTaskStatus(progetto, task, status);
		} else {
			throw new IllegalArgumentException("Stato non valido. Valori ammessi: 'Todo', 'In_progress', 'Done'.");
		}
	}
	
	public void assignTaskTo(Membro membro, Progetto progetto, Attivita task) {
		taskDAO.assignTaskTo(membro, progetto, task);
	}
	
	public boolean inserisciNuovaAttivita(Attivita task, Progetto progetto, Membro assegnatario) {
		int newId = taskDAO.inserisciNuovaAttivita(task, progetto);
		
		if (newId > 0) {
			if (assegnatario != null) {
				taskDAO.assignTaskTo(assegnatario, progetto, task);
			}
			return true;
		}
		return false;
	}
	
}
