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

public class ProgettoGenericoPanel extends JPanel implements ActionListener{
	
	private CreateProjectFrame parentFrame;
	private JButton indietroButton;
	private JButton fineButton;
	private JButton annullaButton;
	private JTextField nomeTextField;
	private JTextField descrizioneTextField;
	private JFormattedTextField dataConsegnaTextField;
	private JRadioButton isProgettoGruppoButton;
	private ErrorLabel errorLabel;
	
	public ProgettoGenericoPanel(Dimension dimension, CreateProjectFrame parentFrame) {

		this.parentFrame = parentFrame;
		this.setPreferredSize(dimension);
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		
		//Pannello centrale
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
		
		
		//Pannello sud dei bottoni
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
		
		JLabel test = new JLabel("Progetto generico");
		
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
				LocalDate dataConsegna = LocalDate.parse(dataConsegnaTextField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				Boolean isProgettoGruppo = isProgettoGruppoButton.isSelected();
				
				ProgettoController progettoController = new ProgettoController();
				if(progettoController.aggiungiNuovoProgettoGenerico(nome, descrizione, dataConsegna, isProgettoGruppo)) {
					
					parentFrame.updateProjects();
					parentFrame.dispose();
					
				}
				
				
			} catch (DateTimeParseException dtex) {
				
				errorLabel.setText("Compilare campi: Data");
				
			} catch(Exception exc) {
				
				errorLabel.setText(exc.getMessage());
				
			}
			
		}
		
	}
	
}
