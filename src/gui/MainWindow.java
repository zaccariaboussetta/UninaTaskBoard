package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import dao.*;
import dao.postgres.UtenteDAOPostgres;
import controllers.*;
import entities.*;

public class MainWindow extends JFrame{
	
	private CardLayout cardLayout;
	private JPanel mainWindowPanels;
	private final int WIDTH_SCREEN;
	private final int HEIGHT_SCREEN;
	private ArrayList<JPanel> listaRiferimentiPannelli;
	
	public MainWindow() {
		
		ImageIcon icon = new ImageIcon("src/duck.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Unina Task Board");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.WIDTH_SCREEN = screenSize.width;
		this.HEIGHT_SCREEN = screenSize.height;
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
		this.listaRiferimentiPannelli = new ArrayList<>();
		
		this.cardLayout = new CardLayout();
		this.mainWindowPanels = new JPanel(cardLayout);
		
		this.add(mainWindowPanels);
		this.setVisible(true);
	}
	

	public void showPanel(String panelNameToShow) { cardLayout.show(mainWindowPanels, panelNameToShow); }
	
	public void addPanel(JPanel newPanel, String nomePanel) { 
		
		newPanel.setName(nomePanel);
		listaRiferimentiPannelli.add(newPanel);

		mainWindowPanels.add(newPanel, nomePanel); 
		
	}
	
	public JPanel getPanelByName(String namePanel) {
		
		for(JPanel p : listaRiferimentiPannelli) {
			
			if(p.getName().equals(namePanel)) return p;
			
		}
		
		return null;
	}
	
	public int getWIDTH() {
		return WIDTH_SCREEN;
	}


	public int getHEIGHT() {
		return HEIGHT_SCREEN;
	}
	
}

