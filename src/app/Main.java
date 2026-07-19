package app;
import gui.*;
import javax.swing.*;

import controllers.*;
import dao.postgres.*;
import dao.*;

public class Main {

	public static void main(String[] args) {

		DatabaseConnection.getInstance();


		ProgettoDAO progettoDAO = new ProgettoDAOPostgres();
		UtenteDAO utenteDAO = new UtenteDAOPostgres();
		MembroDAO membroDAO = new MembroDAOPostgres();
		TaskDAO taskDAO = new TaskDAOPostgres();
		InvitoDAO invitoDAO = new InvitoDAOPostgres();


		//TaskDAO taskDAO_test = new TaskDAOTest();
		//MembroDAO membroDAO_test = new MembroDAOTest();


		AuthController authController = new AuthController(utenteDAO);
		ProgettoController progettoController = new ProgettoController(progettoDAO, invitoDAO);
		RegistrazioneUtenteController registrazioneUtenteController = new RegistrazioneUtenteController(utenteDAO);
		TaskController taskController = new TaskController(taskDAO);
		MembroController membroController = new MembroController(membroDAO);
		DashboardController dashboardController = new DashboardController(membroController, taskController, progettoController);
		SessionController.getInstance();


		MainWindow mainWindow = new MainWindow();


		JPanel loginPanel = new LoginPanel(mainWindow, authController);
		JPanel createAccountPanel = new CreateAccountPanel(mainWindow, registrazioneUtenteController);
		JPanel progettiPanel = new ProgettiPanel(mainWindow, progettoController, membroController);
		JPanel dashboardPanel = new DashboardPanel(mainWindow, dashboardController);

		mainWindow.addPanel(loginPanel, "LOGIN");
		mainWindow.addPanel(createAccountPanel, "CREATE");
		mainWindow.addPanel(progettiPanel, "PROGETTI");
		mainWindow.addPanel(dashboardPanel, "DASHBOARD");

		if (SessionController.getInstance().isUtenteLoggato())
			mainWindow.showPanel("PROGETTI");
		else
			mainWindow.showPanel("LOGIN");

		mainWindow.pack();
		mainWindow.setVisible(true);
	}

}


