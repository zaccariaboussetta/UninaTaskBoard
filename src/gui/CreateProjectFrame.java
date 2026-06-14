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
		
		//Primo pannello - Scelta tipologia progetto.
		JPanel cardPanel1 = new JPanel();
		cardPanel1.setPreferredSize(frameDimension);
		cardPanel1.setLayout(new BoxLayout(cardPanel1, BoxLayout.X_AXIS));
		
		JPanel leftPanel1 = new JPanel();
		leftPanel1.setPreferredSize(new Dimension(350, HEIGHT));
		leftPanel1.setBackground(new Color(0x2B1055));
		
		JPanel rightPanel1 = new JPanel();
		rightPanel1.setPreferredSize(new Dimension(450, HEIGHT));
		rightPanel1.setBackground(new Color(0x4040E0));
		rightPanel1.setLayout(new BoxLayout(rightPanel1, BoxLayout.Y_AXIS));
		
		JLabel titoloLabel = new JLabel("Selezionare tipologia di progetto");
		titoloLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		checkGenerico = new JCheckBox("Progetto - generico");
		checkGenerico.setAlignmentX(LEFT_ALIGNMENT);
		
		checkSviluppo = new JCheckBox("Progetto - sviluppo applicativo");
		checkSviluppo.setAlignmentX(LEFT_ALIGNMENT);
		
		checkEsame = new JCheckBox("Progetto - preparazione esame");
		checkEsame.setAlignmentX(LEFT_ALIGNMENT);
		
		
		
		rightPanel1.add(titoloLabel);
		rightPanel1.add(checkGenerico);
		rightPanel1.add(checkSviluppo);
		rightPanel1.add(checkEsame);
		
		cardPanel1.add(leftPanel1);
		cardPanel1.add(rightPanel1);
	
		//Pannello - Progetto Generico
		JPanel cardPanel2 = new JPanel();
		cardPanel2.setPreferredSize(frameDimension);
		
		
		//Pannello - Progetto Sviluppo App
		JPanel cardPanel3 = new JPanel();
		cardPanel3.setPreferredSize(frameDimension);
		
		
		//Pannello - Progetto Prep Esame
		JPanel cardPanel4 = new JPanel();
		cardPanel4.setPreferredSize(frameDimension);
		
		
		//Pannello - Progetto Svilupp App e Prep Esame
		JPanel cardPanel5 = new JPanel();
		cardPanel5.setPreferredSize(frameDimension);
		
		
		//Pannello dei bottoni
		JPanel buttonsPanel = new JPanel();
		avantiButton = new JButton("Avanti >");
		indietroButton = new JButton("< Indietro");
		fineButton = new JButton("Fine");
		

		buttonsPanel.add(fineButton);
		buttonsPanel.add(indietroButton);
		buttonsPanel.add(avantiButton);
		
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
				
				cardLayout.show(outerPanel,"SVILUPPO ESAME");
				
			}
			else if(checkSviluppo.isSelected()) {
				
				cardLayout.show(outerPanel,"SVILUPPO");
				
			}
			else if(checkEsame.isSelected()) {
				
				cardLayout.show(outerPanel,"ESAME");
				
			}
			else if(checkGenerico.isSelected()) {
				
				cardLayout.show(outerPanel,"GENERICO");
				
			}
			else {
				//TODO: Gestire mancata selezione.
			}
			
		}
		
	}
	
	
}
