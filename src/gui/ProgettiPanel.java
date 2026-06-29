package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import controllers.ProgettoController;
import controllers.SessionController;
import entities.Progetto;

public class ProgettiPanel extends JPanel implements ActionListener {
	
	private MainWindow mainWindow;
	private ProgettoController progettoController;
	private JButton openButton;
	private JButton createButton;
	private JPanel progettiUtentePanel;
	private JTextArea descrizioneTextArea;
	
	private int idProgettoSelezionato;
	
	public ProgettiPanel(MainWindow mainWindow, ProgettoController progettoController) {
		
		this.mainWindow = mainWindow;
		this.progettoController = progettoController;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == openButton) {
			try {
			
				progettoController.setProgettoById(idProgettoSelezionato);
				
				((DashboardPanel)mainWindow.getPanelByName("DASHBOARD")).updateOnSelectedProject();
				mainWindow.showPanel("DASHBOARD");
			}
			catch(Exception ex) {
				//TODO: 
			}
		}
		
		if(e.getSource() == createButton) {
			
			new CreateProjectFrame(this, progettoController);
			this.setCreateButtonTo(false);
			
		}
	}
	
	public void setCreateButtonTo(boolean state) { createButton.setEnabled(state); }
	
	public void loadProjects() {
		try {
			ImageIcon folderStatica = new ImageIcon("src/folderStatica.png");
			Image scaledFolderIcon = folderStatica.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			folderStatica = new ImageIcon(scaledFolderIcon);
			
			ImageIcon folderHover = new ImageIcon("src/folderHover.png");
			Image scaledFolderHHoverIcon = folderHover.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			folderHover = new ImageIcon(scaledFolderHHoverIcon);
			
			ImageIcon folderSelezionata = new ImageIcon("src/folderSelezionata.png");
			Image scaledFolderSelezioanaIcon = folderSelezionata.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			folderSelezionata = new ImageIcon(scaledFolderSelezioanaIcon);
			
			ButtonGroup gruppoRadioButton = new ButtonGroup();
			progettiUtentePanel.removeAll();
			
			if (descrizioneTextArea != null) {
				descrizioneTextArea.setText("");
			}
			
			for(Progetto prog : progettoController.getProgettiUtente()) {
				
				JRadioButton radioButton = new ProgettoRadioButton(prog.getNome(), folderStatica);
				radioButton.setRolloverIcon(folderHover);
				radioButton.setSelectedIcon(folderSelezionata);
				
				radioButton.addActionListener(this);
				
				radioButton.addActionListener(e -> {
					String desc = prog.getDescrizione();
					descrizioneTextArea.setText((desc != null && !desc.isBlank()) ? desc : "Nessuna descrizione disponibile per questo progetto.");
					idProgettoSelezionato = prog.getIdProgetto();
				});
				
				gruppoRadioButton.add(radioButton);
				progettiUtentePanel.add(radioButton);
			}
			
			progettiUtentePanel.revalidate();
			progettiUtentePanel.repaint();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateOnLogin() {
		
		this.removeAll();
		
		this.setBackground(new Color(0xBDCAF2));
		this.setLayout(new GridBagLayout());
		
		JPanel innerWelcomePanel = new JPanel();
		innerWelcomePanel.setBackground(Color.WHITE);
		innerWelcomePanel.setPreferredSize(new Dimension(850, 550));
		
		innerWelcomePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.BLACK, 1),
				BorderFactory.createEmptyBorder(20, 20, 20, 20)
		));
		innerWelcomePanel.setLayout(new BorderLayout(20, 20));
		
		String nomeUtente = SessionController.getInstance().getUtenteLoggato().getNome(); 
		
		JLabel welcomeLabel = new JLabel("Welcome to Unina Task Board, " + nomeUtente + " !");
		welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
		centerPanel.setBackground(Color.WHITE);
		
		progettiUtentePanel = new JPanel();
		progettiUtentePanel.setBackground(Color.WHITE);
		progettiUtentePanel.setLayout(new GridLayout(0, 1, 2, 2)); 
		
		JScrollPane scrollProjectsPanel = new JScrollPane(progettiUtentePanel);
		scrollProjectsPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), "I tuoi progetti"
		));
		scrollProjectsPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel descriptionPanel = new JPanel(new BorderLayout());
		descriptionPanel.setBackground(Color.WHITE);
		descriptionPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), "Descrizione Progetto"
		));
		
		descrizioneTextArea = new JTextArea();
		descrizioneTextArea.setEditable(false);
		descrizioneTextArea.setLineWrap(true);
		descrizioneTextArea.setWrapStyleWord(true);
		descrizioneTextArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
		descrizioneTextArea.setMargin(new java.awt.Insets(10, 10, 10, 10));
		
		JScrollPane scrollDescPanel = new JScrollPane(descrizioneTextArea);
		scrollDescPanel.setBorder(BorderFactory.createEmptyBorder());
		
		descriptionPanel.add(scrollDescPanel, BorderLayout.CENTER);
		
		centerPanel.add(scrollProjectsPanel);
		centerPanel.add(descriptionPanel);
		
		this.loadProjects();
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setBackground(Color.WHITE);
		
		openButton = new JButton("Apri");
		openButton.addActionListener(this);
		createButton = new JButton("Crea");
		createButton.addActionListener(this);
		
		buttonsPanel.add(openButton);
		buttonsPanel.add(createButton);
		
		innerWelcomePanel.add(welcomeLabel, BorderLayout.NORTH);
		innerWelcomePanel.add(centerPanel, BorderLayout.CENTER);
		innerWelcomePanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		this.add(innerWelcomePanel);
		
		this.revalidate();
		this.repaint();
		
	}
}
