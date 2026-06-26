package app;
import gui.*;
import javax.swing.*;

import controllers.*;
import dao.postgres.*;
import dao.*;

public class Main {

	public static void main(String[] args) {
		//Connessione al database
		DatabaseConnection.getInstance(); 
		
		//Inizializzazione di tutti i DAO
		ProgettoDAO progettoDAO = new ProgettoDAOPostgres();
		UtenteDAO utenteDAO = new UtenteDAOPostgres();
		MembroDAO membroDAO = new MembroDAOPostgres();
		TaskDAO taskDAO = new TaskDAOPostgres();
		
		
		//Inizializzazione dei controller e passaggio dei relativi DAO
		AuthController authController = new AuthController(utenteDAO);
		ProgettoController progettoController = new ProgettoController(progettoDAO);
		RegistrazioneUtenteController registrazioneUtenteController = new RegistrazioneUtenteController(utenteDAO);
		TaskController taskController = new TaskController(taskDAO);
		MembroController MembroController = new MembroController(membroDAO);
		SessionController.getInstance(); 
		
		//Inizializzazione della finestra principale dell'applicativo
		MainWindow mainWindow = new MainWindow();
		
		//Inizializzazione dei pannelli 
		JPanel loginPanel = new LoginPanel(mainWindow, authController);
		JPanel createAccountPanel = new CreateAccountPanel(mainWindow, registrazioneUtenteController);
		JPanel progettiPanel = new ProgettiPanel(mainWindow, progettoController);
		
		mainWindow.addPanel(loginPanel, "LOGIN");
		mainWindow.addPanel(createAccountPanel, "CREATE");
		mainWindow.addPanel(progettiPanel, "PROGETTI");
		
		if (SessionController.getInstance().isUtenteLoggato()) 
			mainWindow.showPanel("PROGETTI");
		else 
			mainWindow.showPanel("LOGIN");
		
		mainWindow.pack();
		mainWindow.setVisible(true);
	}

}
