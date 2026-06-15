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

public class CreateProjectFrame extends JFrame implements ActionListener{
	
	private final int WEIGHT = 800;
	private final int HEIGHT = 500;
	private final Dimension frameDimension = new Dimension(WEIGHT, HEIGHT);
	private CardLayout cardLayout;
	private JPanel outerPanel;
	private JButton avantiButton;
	private JButton indietroButton;
	private JButton fineButton;
	private JButton annullaButton;
	private JCheckBox checkGenerico;
	private JCheckBox checkSviluppo;
	private JCheckBox checkEsame;
	
	public CreateProjectFrame() {
		
		this.setTitle("Wizard - Creazione progetto - Unina Task Board");
		this.setSize(WEIGHT, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		cardLayout = new CardLayout();
		outerPanel = new JPanel(cardLayout);
		
		JPanel cardPanel1 = new SelezioneTipologiaProgettoPanel(frameDimension);
		
		JPanel cardPanel2 = new ProgettoGenericoPanel(frameDimension);
		
		JPanel cardPanel3 = new ProgettoSviluppoPanel(frameDimension);
		
		JPanel cardPanel4 = new ProgettoPrepEsamePanel(frameDimension);
		
		JPanel cardPanel5 = new ProgettoSviluppoPrepEsamePanel(frameDimension);
		
		
		//Pannello dei bottoni
		JPanel buttonsPanel = new JPanel();
		
		avantiButton = new JButton("Avanti >");
		avantiButton.addActionListener(this);
		
		indietroButton = new JButton("< Indietro");
		indietroButton.setEnabled(false);
		indietroButton.addActionListener(this);
		
		fineButton = new JButton("Fine");
		fineButton.setEnabled(false);
		fineButton.addActionListener(this);
		
		annullaButton = new JButton("Annulla");
		annullaButton.addActionListener(this);
	
		buttonsPanel.add(annullaButton);
		buttonsPanel.add(indietroButton);
		buttonsPanel.add(avantiButton);
		buttonsPanel.add(fineButton);
		
		
		outerPanel.add(cardPanel1, "SELEZIONE");
		outerPanel.add(cardPanel2, "GENERICO");
		outerPanel.add(cardPanel3, "SVILUPPO");
		outerPanel.add(cardPanel4, "ESAME");
		outerPanel.add(cardPanel5, "SVILUPPO ESAME");
		
		this.add(outerPanel, BorderLayout.CENTER);
		this.add(buttonsPanel, BorderLayout.SOUTH);
		cardLayout.show(outerPanel,"SELEZIONE");
		this.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == avantiButton) {
			
			if(checkSviluppo.isSelected() && checkEsame.isSelected()) {
				
				fineButton.setEnabled(true);
				indietroButton.setEnabled(true);
				avantiButton.setEnabled(false);
				cardLayout.show(outerPanel,"SVILUPPO ESAME");
				
			}
			else if(checkSviluppo.isSelected()) {
				
				fineButton.setEnabled(true);
				indietroButton.setEnabled(true);
				avantiButton.setEnabled(false);
				cardLayout.show(outerPanel,"SVILUPPO");
				
			}
			else if(checkEsame.isSelected()) {
				
				fineButton.setEnabled(true);
				indietroButton.setEnabled(true);
				avantiButton.setEnabled(false);
				cardLayout.show(outerPanel,"ESAME");
				
			}
			else if(checkGenerico.isSelected()) {
				
				fineButton.setEnabled(true);
				indietroButton.setEnabled(true);
				avantiButton.setEnabled(false);
				cardLayout.show(outerPanel,"GENERICO");
				
			}
			else {
				//TODO: Gestire mancata selezione.
			}
			
		}
		
		if(e.getSource() == indietroButton) {
			
			avantiButton.setEnabled(true);
			indietroButton.setEnabled(false);
			
		}
		
		
		if(e.getSource() == fineButton) {
			
			//TODO: Logica di reperimento dati dai textfield e chiamata al controller di progetto.
			
		}
		if(e.getSource() == annullaButton) {
			
			this.dispose();
			
		}
		
	}
	
	
}
