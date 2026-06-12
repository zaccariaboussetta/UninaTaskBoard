package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.*;
import java.awt.Dimension;

public class WindowApp extends JFrame{
	
	private ImageIcon icon;
	private CardLayout cardLayout;
	private JPanel allPanels;
	private final int WIDTH = 1920;
	private final int LENGTH = 1080;
	
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

		//Instances of all the possible panels in the applications
		LoginPanel loginPanel = new LoginPanel(this);
		allPanels.add(loginPanel, "LOGIN");
		
		CreateAccountPanel createAccountPanel = new CreateAccountPanel(this);
		allPanels.add(createAccountPanel, "CREATE");
		
		WelcomePanel dashboardPanel = new WelcomePanel(this);
		allPanels.add(dashboardPanel,"WELCOME");
	
		
		this.add(allPanels);
		cardLayout.show(allPanels,"LOGIN");
		this.setVisible(true);
	}
	
	public void showPanel(String panelNameToShow) {
		cardLayout.show(allPanels, panelNameToShow);
	}
	
}
