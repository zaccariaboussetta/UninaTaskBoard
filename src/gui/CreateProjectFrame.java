package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.ProgettoController;

public class CreateProjectFrame extends JFrame{
	
	private ProgettiPanel parentPanel;  
	private ProgettoController progettoController;
	private final int WEIGHT = 800;
	private final int HEIGHT = 500;
	private final Dimension frameDimension = new Dimension(WEIGHT, HEIGHT);
	private CardLayout cardLayout;
	private JPanel outerPanel;
	private SelezioneTipologiaProgettoPanel cardPanel1;
	private ProgettoGenericoPanel cardPanel2;
	private ProgettoSviluppoPanel cardPanel3;
	private ProgettoPrepEsamePanel cardPanel4;
	private ProgettoSviluppoPrepEsamePanel cardPanel5;
	
	public CreateProjectFrame(ProgettiPanel pp, ProgettoController pc) {
		
		this.parentPanel = pp;
		this.progettoController = pc;
		
		this.setTitle("Wizard - Creazione progetto - Unina Task Board");
		this.setSize(WEIGHT, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		cardLayout = new CardLayout();
		outerPanel = new JPanel(cardLayout);
		
		cardPanel1 = new SelezioneTipologiaProgettoPanel(frameDimension, this);
		
		cardPanel2 = new ProgettoGenericoPanel(frameDimension,this);
		
		cardPanel3 = new ProgettoSviluppoPanel(frameDimension, this);
		
		cardPanel4 = new ProgettoPrepEsamePanel(frameDimension, this);
		
		cardPanel5 = new ProgettoSviluppoPrepEsamePanel(frameDimension, this);
		
		
		outerPanel.add(cardPanel1, "SELEZIONE");
		outerPanel.add(cardPanel2, "GENERICO");
		outerPanel.add(cardPanel3, "SVILUPPO");
		outerPanel.add(cardPanel4, "ESAME");
		outerPanel.add(cardPanel5, "SVILUPPO ESAME");
		
		this.add(outerPanel, BorderLayout.CENTER);
		cardLayout.show(outerPanel,"SELEZIONE");
		this.setVisible(true);
	}

	public void showPanel(String panelNameToShow) {
		
		cardLayout.show(outerPanel, panelNameToShow);
		
	}
	
	public void updateProjects() {
		
		parentPanel.loadProjects();
		parentPanel.setCreateButtonTo(true);
		
	}
	
	public ProgettoController getProgettoController() {
		
		return this.progettoController;
		
	}
	
	
	
}

