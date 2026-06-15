package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelezioneTipologiaProgettoPanel extends JPanel{
	
	public SelezioneTipologiaProgettoPanel(Dimension dimension) {
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
		
		JCheckBox checkGenerico = new JCheckBox("Progetto - generico");
		checkGenerico.setAlignmentX(LEFT_ALIGNMENT);
		
		JCheckBox checkSviluppo = new JCheckBox("Progetto - sviluppo applicativo");
		checkSviluppo.setAlignmentX(LEFT_ALIGNMENT);
		
		JCheckBox checkEsame = new JCheckBox("Progetto - preparazione esame");
		checkEsame.setAlignmentX(LEFT_ALIGNMENT);
		
		
		
		rightPanel1.add(titoloLabel);
		rightPanel1.add(checkGenerico);
		rightPanel1.add(checkSviluppo);
		rightPanel1.add(checkEsame);
		
		this.add(leftPanel1);
		this.add(rightPanel1);
	}

}
