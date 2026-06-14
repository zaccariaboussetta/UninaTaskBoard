package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.SessionController;
import entities.Utente;
//TODO: Settare action listener per i bottoni

public class WelcomePanel extends JPanel implements ActionListener{
	
	private WindowApp windowApplication;
	private JButton showButton;
	private JButton createProjectButton;
	
	public WelcomePanel(WindowApp windowApp) {
		this.windowApplication = windowApp;
		
		this.setBackground(new Color(0xBDCAF2));
		this.setLayout(new GridBagLayout());
		
		JPanel innerWelcomePanel = new JPanel();
		innerWelcomePanel.setBackground(Color.WHITE);
		innerWelcomePanel.setPreferredSize(new Dimension(700, 500));
		innerWelcomePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		innerWelcomePanel.setLayout(new BoxLayout(innerWelcomePanel, BoxLayout.Y_AXIS));
		
		
		JLabel welcomeLabel = new JLabel("Welcome to Unina Task Board !");
		welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.WHITE);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		showButton = new StyledButton("Mostra progetti");
		
		createProjectButton = new StyledButton("Crea progetto");
		
		buttonsPanel.add(showButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(50,0)));
		buttonsPanel.add(createProjectButton);
		
		
		innerWelcomePanel.add(Box.createRigidArea(new Dimension(0, 100)));
		innerWelcomePanel.add(welcomeLabel);
		innerWelcomePanel.add(Box.createRigidArea(new Dimension(0, 100)));
		innerWelcomePanel.add(buttonsPanel);
		
		this.add(innerWelcomePanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == showButton) {
			
		}
		
		if(e.getSource() == createProjectButton) {
			
		}
		
	}
}
