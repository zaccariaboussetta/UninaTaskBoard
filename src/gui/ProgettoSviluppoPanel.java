package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controllers.ProgettoController;

public class ProgettoSviluppoPanel extends JPanel implements ActionListener {
	
	private CreateProjectFrame parentFrame;
	private JButton indietroButton;
	private JButton fineButton;
	private JButton annullaButton;
	private JTextField nomeTextField;
	private JTextField descrizioneTextField;
	private JFormattedTextField dataConsegnaTextField;
	private JRadioButton isProgettoGruppoButton;
	private ErrorLabel errorLabel;
	
	// Campi specifici per lo Sviluppo Applicativi
	private JTextField repositoryTextField;
	private JTextField techStackTextField;
	private JTextField versioneTextField;
	
	public ProgettoSviluppoPanel(Dimension dimension, CreateProjectFrame parentFrame) {

		this.parentFrame = parentFrame;
		this.setPreferredSize(dimension);
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		
		JPanel textFieldsPanel = new JPanel(new java.awt.GridBagLayout());
		textFieldsPanel.setBackground(Color.WHITE);

		java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
		gbc.insets = new java.awt.Insets(10, 15, 10, 15); 
		gbc.anchor = java.awt.GridBagConstraints.WEST;    

		nomeTextField = new JTextField(25);
		descrizioneTextField = new JTextField(25);

		try {
		    MaskFormatter maschera = new MaskFormatter("##/##/####");
		    maschera.setPlaceholderCharacter('_');
		    dataConsegnaTextField = new JFormattedTextField(maschera);
		    dataConsegnaTextField.setColumns(10);
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		isProgettoGruppoButton = new JRadioButton("Sì, è un progetto di gruppo");
		isProgettoGruppoButton.setBackground(Color.WHITE);

		// Inizializzazione nuovi campi
		repositoryTextField = new JTextField(25);
		techStackTextField = new JTextField(25);
		versioneTextField = new JTextField(25);

		// Disposizione Griglia Comune
		gbc.gridx = 0; gbc.gridy = 0;
		textFieldsPanel.add(new JLabel("Nome Progetto:"), gbc);
		gbc.gridx = 1; 
		textFieldsPanel.add(nomeTextField, gbc);

		gbc.gridx = 0; gbc.gridy = 1;
		textFieldsPanel.add(new JLabel("Descrizione:"), gbc);
		gbc.gridx = 1;
		textFieldsPanel.add(descrizioneTextField, gbc);
		
		gbc.gridx = 0; gbc.gridy = 2;
		textFieldsPanel.add(new JLabel("Data Scadenza:"), gbc);
		gbc.gridx = 1;
		textFieldsPanel.add(dataConsegnaTextField, gbc);

		gbc.gridx = 0; gbc.gridy = 3;
		textFieldsPanel.add(new JLabel("Progetto di gruppo?"), gbc);
		gbc.gridx = 1;
		textFieldsPanel.add(isProgettoGruppoButton, gbc);
		
		// Disposizione Campi Specifici Sviluppo
		gbc.gridx = 0; gbc.gridy = 4;
		textFieldsPanel.add(new JLabel("Repository URL (Opzionale):"), gbc);
		gbc.gridx = 1;
		textFieldsPanel.add(repositoryTextField, gbc);

		gbc.gridx = 0; gbc.gridy = 5;
		textFieldsPanel.add(new JLabel("Tech Stack (Opzionale):"), gbc);
		gbc.gridx = 1;
		textFieldsPanel.add(techStackTextField, gbc);

		gbc.gridx = 0; gbc.gridy = 6;
		textFieldsPanel.add(new JLabel("Versione (Opzionale):"), gbc);
		gbc.gridx = 1;
		textFieldsPanel.add(versioneTextField, gbc);
		
		// Pannello Bottoni
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		indietroButton = new JButton("< Indietro");
		indietroButton.addActionListener(this);
		fineButton = new JButton("Fine");
		fineButton.addActionListener(this);
		annullaButton = new JButton("Annulla");
		annullaButton.addActionListener(this);
		
		errorLabel = new ErrorLabel();
		buttonsPanel.add(errorLabel);
		buttonsPanel.add(annullaButton);
		buttonsPanel.add(indietroButton);
		buttonsPanel.add(fineButton);
		
		this.add(textFieldsPanel, BorderLayout.CENTER);
		this.add(buttonsPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == indietroButton) {
			parentFrame.showPanel("SELEZIONE");
		}
		if(e.getSource() == annullaButton) {
			parentFrame.dispose();
		}
		if(e.getSource() == fineButton) {
		    try {
		        String nome = nomeTextField.getText();
		        String descrizione = descrizioneTextField.getText();
		        Boolean isProgettoGruppo = isProgettoGruppoButton.isSelected();
		        
		        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        LocalDate dataConsegna = LocalDate.parse(dataConsegnaTextField.getText(), dtf);
		        
		        // Stringhe passate direttamente senza controlli di blocco .isBlank()
		        String repo = repositoryTextField.getText();
		        String tech = techStackTextField.getText();
		        String versione = versioneTextField.getText();
		        
		        ProgettoController progettoController = new ProgettoController();
		        if(progettoController.aggiungiNuovoProgettoEsameSviluppo(nome, descrizione, dataConsegna, isProgettoGruppo, repo, tech, versione)) {
		            parentFrame.updateProjects();
		            parentFrame.dispose();
		        }
		        
		    } catch (DateTimeParseException dtex) {
		        errorLabel.setText("Formato data non valido (GG/MM/AAAA).");
		    } catch(Exception exc) {
		        errorLabel.setText(exc.getMessage());
		    }
		}
	}
}