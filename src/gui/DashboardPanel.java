package gui;

import javax.swing.JPanel;

import controllers.*;

public class DashboardPanel extends JPanel{

	private MainWindow mainWindow;
	private MembroController membroController;
	private TaskController taskController;
	private ProgettoController progettoController;
	
	public DashboardPanel(MainWindow mainWindow, MembroController mc, TaskController tc, ProgettoController pc) {
		this.mainWindow = mainWindow;
		this.membroController = mc;
		this.taskController = tc;
		this.progettoController = pc;
	}
	
	public void updateOnSelectedProject() {	
		//TODO: L'intera Grafica.
	}
}

