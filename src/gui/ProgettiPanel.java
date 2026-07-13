package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    private JLabel dataCreazioneLabel;
    
    private int idProgettoSelezionato;
    
    public ProgettiPanel(MainWindow mainWindow, ProgettoController progettoController) {
        
        this.mainWindow = mainWindow;
        this.progettoController = progettoController;
        this.setLayout(new BorderLayout()); // Assicura che il mainPanel occupi tutto lo spazio
        
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
                ex.printStackTrace();
            }
        }
        
        if(e.getSource() == createButton) {
            new CreateProjectFrame(mainWindow,this, "Creazione progetti", true, progettoController);
        }
    }
    
    public void selectedProg(int idProg, String desc, LocalDate date) {
    	this.idProgettoSelezionato = idProg;
    	descrizioneTextArea.setText(desc);
    	dataCreazioneLabel.setText(date.toString());
    }
    
    public void loadProgetti() {
        try {
         
            ButtonGroup gruppoRadioButton = new ButtonGroup();
            
            progettiUtentePanel.removeAll();
            
            for(Progetto prog : progettoController.getProgettiUtente()) {
                
                JRadioButton radioButton = new ProgettoRadioButton(this, prog.getNome(), prog.getIdProgetto(), prog.getDataCreazione(), prog.getDescrizione());
                
                gruppoRadioButton.add(radioButton);
                progettiUtentePanel.add(radioButton);
            }
            
            progettiUtentePanel.revalidate();
            progettiUtentePanel.repaint();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        
        this.removeAll();
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(850, 550));
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margine interno per non schiacciare i bordi
        
        String nomeUtente = SessionController.getInstance().getUtenteLoggato().getNome(); 
        
        JPanel northPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        northPanel.setBackground(Color.WHITE);
        
        JLabel welcomeLabel = new JLabel("Ciao, " + nomeUtente + " !");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel descrizioneLabel = new JLabel("Seleziona un progetto o creane uno nuovo.");
        descrizioneLabel.setHorizontalAlignment(JLabel.CENTER);
        
        northPanel.add(welcomeLabel);
        northPanel.add(descrizioneLabel);
        

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBackground(Color.WHITE);
        
        // --- PARTE SINISTRA: Lista Progetti ---
        progettiUtentePanel = new JPanel();
        progettiUtentePanel.setBackground(Color.WHITE);
        progettiUtentePanel.setLayout(new BoxLayout(progettiUtentePanel, BoxLayout.Y_AXIS)); 
        
        JScrollPane scrollProjectsPanel = new JScrollPane(progettiUtentePanel);
        scrollProjectsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), "Progetti selezionabili"
        ));
        scrollProjectsPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        // --- PARTE DESTRA: Data e Descrizione ---
        JPanel rightSidePanel = new JPanel(new BorderLayout(0, 10));
        rightSidePanel.setBackground(Color.WHITE);
        
        // Destra - TOP (Data creazione)
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), "Dettagli progetto"
        ));
        dataCreazioneLabel = new JLabel("Nessun progetto selezionato");
        dataCreazioneLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailsPanel.add(dataCreazioneLabel, BorderLayout.CENTER);
        
        // Destra - CENTER (Descrizione)
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
        
        
        rightSidePanel.add(detailsPanel, BorderLayout.NORTH);
        rightSidePanel.add(descriptionPanel, BorderLayout.CENTER);
        
        
        centerPanel.add(scrollProjectsPanel);
        centerPanel.add(rightSidePanel);
        
        this.loadProgetti();
        
        // --- PARTE BASSA: Bottoni ---
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBackground(Color.WHITE);
        
        openButton = new JButton("Apri");
        openButton.addActionListener(this);
        createButton = new JButton("Crea");
        createButton.addActionListener(this);
        
        buttonsPanel.add(openButton);
        buttonsPanel.add(createButton);
        
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel, BorderLayout.CENTER);
        
        mainWindow.pack();
        this.revalidate();
        this.repaint();
        
    }
}
