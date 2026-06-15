package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProgettoSviluppoPrepEsamePanel extends JPanel {
	
	private CreateProjectFrame parentFrame;

	public ProgettoSviluppoPrepEsamePanel(Dimension dimension, CreateProjectFrame parentFrame) {
		
		this.parentFrame = parentFrame;
		this.setPreferredSize(dimension);
		this.setBackground(Color.cyan);
		JLabel test = new JLabel("Progetto sviluppo e esame");
		
		this.add(test);
	}
	
}
