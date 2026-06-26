package controllers;

import dao.TaskDAO;

public class TaskController {

	TaskDAO taskDAO;
	
	public TaskController(TaskDAO t) {
		
		this.taskDAO = t;
		
	}
	
}
