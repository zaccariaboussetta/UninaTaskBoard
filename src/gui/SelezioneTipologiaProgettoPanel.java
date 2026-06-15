package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SelezioneTipologiaProgettoPanel extends JPanel implements ActionListener{
	
	private CreateProjectFrame parentFrame;
	private JButton avantiButton;
	private JButton indietroButton;
	private JButton annullaButton;
	private JCheckBox checkSviluppo;
	private JCheckBox checkGenerico;
	private JCheckBox checkEsame;
	
	
	public SelezioneTipologiaProgettoPanel(Dimension dimension, CreateProjectFrame parentFrame) {
		
		this.parentFrame = parentFrame;
		this.setPreferredSize(dimension);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel leftPanel1 = new JPanel();
		leftPanel1.setPreferredSize(new Dimension(350, HEIGHT));
		leftPanel1.setBackground(new Color(0x2B1055));
		
		JPanel rightPanel1 = new JPanel();
		rightPanel1.setPreferredSize(new Dimension(450, HEIGHT));
		rightPanel1.setBackground(Color.white);
		rightPanel1.setLayout(new BoxLayout(rightPanel1, BoxLayout.Y_AXIS));
		
		JLabel titoloLabel = new JLabel("Selezionare tipologia di progetto");
		titoloLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		checkGenerico = new JCheckBox("Progetto - generico");
		checkGenerico.setAlignmentX(LEFT_ALIGNMENT);
		
		checkSviluppo = new JCheckBox("Progetto - sviluppo applicativo");
		checkSviluppo.setAlignmentX(LEFT_ALIGNMENT);
		
		checkEsame = new JCheckBox("Progetto - preparazione esame");
		checkEsame.setAlignmentX(LEFT_ALIGNMENT);
		
		//Bottoni
		JPanel buttonsPanel = new JPanel();
		
		avantiButton = new JButton("Avanti >");
		avantiButton.addActionListener(this);
		
		annullaButton = new JButton("Annulla");
		annullaButton.addActionListener(this);
	
		buttonsPanel.add(annullaButton);
		buttonsPanel.add(avantiButton);
		
		rightPanel1.add(titoloLabel);
		rightPanel1.add(checkGenerico);
		rightPanel1.add(checkSviluppo);
		rightPanel1.add(checkEsame);
		
		this.add(leftPanel1);
		this.add(rightPanel1);
		this.add(buttonsPanel);
	}

	@Override
public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == avantiButton) {
			
			if(checkSviluppo.isSelected() && checkEsame.isSelected()) {
				
				parentFrame.showPanel("SVILUPPO ESAME");
				
			}
			else if(checkSviluppo.isSelected()) {
				
				parentFrame.showPanel("SVILUPPO");
				
			}
			else if(checkEsame.isSelected()) {
				
				parentFrame.showPanel("ESAME");
				
			}
			else if(checkGenerico.isSelected()) {
				
				parentFrame.showPanel("GENERICO");
				
			}
			else {
				//TODO: Gestire mancata selezione.
			}
			
		}
		
		if(e.getSource() == indietroButton) {
			
			avantiButton.setEnabled(true);
			indietroButton.setEnabled(false);
			
		}
		
		
		if(e.getSource() == annullaButton) {
			
			parentFrame.dispose();
			
		}
		
	}

}
