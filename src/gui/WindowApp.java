package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;

import dao.*;
import dao.postgres.UtenteDAOPostgres;
import controllers.*;
import entities.*;

public class WindowApp extends JFrame{
	
	private ImageIcon icon;
	private CardLayout cardLayout;
	private JPanel allPanels;
	private final int WIDTH_SCREEN;
	private final int HEIGHT_SCREEN;
	
	private AuthController authController;
	private RegistrazioneUtenteController registrazioneUtenteController;
	
	public WindowApp() {
		this.icon = new ImageIcon("src/duck.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Unina Task Board");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.WIDTH_SCREEN = screenSize.width;
		this.HEIGHT_SCREEN = screenSize.height;
		
		this.setSize((int)(WIDTH_SCREEN * 0.50), (int)(HEIGHT_SCREEN * 0.70));
		this.setMinimumSize(new Dimension((int)(WIDTH_SCREEN * 0.40), (int)(HEIGHT_SCREEN * 0.60)));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
		this.cardLayout = new CardLayout();
		this.allPanels = new JPanel(cardLayout);
		
		this.createControllers(); //Istanzia tutti i controllers e i dao necessari agli altri pannelli

		//Instances of all the possible panels in the applications
		LoginPanel loginPanel = new LoginPanel(this, authController);
		allPanels.add(loginPanel, "LOGIN");
		
		CreateAccountPanel createAccountPanel = new CreateAccountPanel(this, registrazioneUtenteController);
		allPanels.add(createAccountPanel, "CREATE");
		
		WelcomePanel dashboardPanel = new WelcomePanel(this);
		allPanels.add(dashboardPanel,"WELCOME");
	
		
		this.add(allPanels);
		cardLayout.show(allPanels,"LOGIN");
		this.setVisible(true);
	}
	
	private void createControllers() {
		
		UtenteDAO utenteDAO = new UtenteDAOPostgres();
		authController = new AuthController(utenteDAO);
		registrazioneUtenteController = new RegistrazioneUtenteController(utenteDAO);
		
	}

	public void showPanel(String panelNameToShow) {
		cardLayout.show(allPanels, panelNameToShow);
	}
	
}
