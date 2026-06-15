package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProgettoSviluppoPanel extends JPanel {
	
	private CreateProjectFrame parentFrame;

	public ProgettoSviluppoPanel(Dimension dimension, CreateProjectFrame parentFrame) {
		
		this.parentFrame = parentFrame;
		this.setPreferredSize(dimension);
		this.setBackground(Color.cyan);
		JLabel test = new JLabel("Progetto sviluppo");
		
		this.add(test);
	}
	
}
