package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.*;
import java.awt.Dimension;

import dao.*;
import dao.postgres.UtenteDAOPostgres;
import controllers.*;
import entities.*;

public class WindowApp extends JFrame{
	
	private ImageIcon icon;
	private CardLayout cardLayout;
	private JPanel allPanels;
	private final int WIDTH = 1920;
	private final int LENGTH = 1080;
	
	private AuthController authController;
	
	public WindowApp() {
		this.icon = new ImageIcon("src/duck.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Unina Task Board");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setSize(WIDTH, LENGTH);
		this.setMinimumSize(new Dimension(WIDTH/2, LENGTH));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
		this.cardLayout = new CardLayout();
		this.allPanels = new JPanel(cardLayout);
		
		this.createControllers(); //Istanzia tutti i controllers e i dao necessari agli altri pannelli

		//Instances of all the possible panels in the applications
		LoginPanel loginPanel = new LoginPanel(this, authController);
		allPanels.add(loginPanel, "LOGIN");
		
		CreateAccountPanel createAccountPanel = new CreateAccountPanel(this);
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
		
	}

	public void showPanel(String panelNameToShow) {
		cardLayout.show(allPanels, panelNameToShow);
	}
	
}
