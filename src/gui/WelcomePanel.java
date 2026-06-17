package gui;

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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.ProgettoController;
import controllers.SessionController;
import dao.ProgettoDAO;
import dao.postgres.ProgettoDAOPostgres;
import entities.Progetto;
import entities.Utente;

public class WelcomePanel extends JPanel implements ActionListener{
	
	private WindowApp windowApplication;
	private JButton openButton;
	private JButton createButton;
	private JPanel progettiUtentePanel;
	
	public WelcomePanel(WindowApp windowApp) {
		this.windowApplication = windowApp;
		
		this.setBackground(new Color(0xBDCAF2));
		this.setLayout(new GridBagLayout());
		
		JPanel innerWelcomePanel = new JPanel();
		innerWelcomePanel.setBackground(Color.WHITE);
		innerWelcomePanel.setPreferredSize(new Dimension(700, 500));
		innerWelcomePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		innerWelcomePanel.setLayout(new BoxLayout(innerWelcomePanel, BoxLayout.Y_AXIS));
		
		String nomeUtente = SessionController.getInstance().getUtenteLoggato().getNome(); 
		
		JLabel welcomeLabel = new JLabel("Welcome to Unina Task Board, " + nomeUtente + " !");
		welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		
		
		progettiUtentePanel = new JPanel();
		progettiUtentePanel.setBackground(Color.WHITE);
		progettiUtentePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		progettiUtentePanel.setLayout(new GridLayout(0, 1, 2, 2)); 
		
		this.loadProjects();

		
		JScrollPane scrollProjectsPanel = new JScrollPane(progettiUtentePanel);
		scrollProjectsPanel.setPreferredSize(new Dimension(500, 400));
		scrollProjectsPanel.setBorder(BorderFactory.createEmptyBorder());
		scrollProjectsPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		openButton = new StyledButton("Apri");
		openButton.addActionListener(this);
		createButton = new StyledButton("Crea");;
		createButton.addActionListener(this);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.add(openButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		buttonsPanel.add(createButton);
		
		
		innerWelcomePanel.add(Box.createRigidArea(new Dimension(0, 25)));
		innerWelcomePanel.add(welcomeLabel);
		innerWelcomePanel.add(Box.createRigidArea(new Dimension(0, 25)));
		innerWelcomePanel.add(scrollProjectsPanel);
		innerWelcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		innerWelcomePanel.add(buttonsPanel);
		innerWelcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		this.add(innerWelcomePanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == openButton) {
			
		}
		
		if(e.getSource() == createButton) {
			
			JFrame createProjectFrame = new CreateProjectFrame(this);
		}
		
	}
	
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
			
			ProgettoController progettoController = new ProgettoController();
			ArrayList<Progetto> listProgetto = progettoController.getProgettiUtente();
			
			ButtonGroup gruppoRadioButton = new ButtonGroup();
			progettiUtentePanel.removeAll();
			
			for(Progetto prog : listProgetto) {
				
				JRadioButton radioButton = new ProgettoRadioButton(prog.getNome(), folderStatica);
				radioButton.setRolloverIcon(folderHover);
				radioButton.setSelectedIcon(folderSelezionata);
				
				radioButton.addActionListener(this);
				
				
				gruppoRadioButton.add(radioButton);
				
				progettiUtentePanel.add(radioButton);
			}
			
			progettiUtentePanel.revalidate();
			progettiUtentePanel.repaint();
			
		} catch (Exception e) {
			//TODO: Handle this exception.
			e.printStackTrace();
		}
		
	}
}
