package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProgettoGenericoPanel extends JPanel implements ActionListener{
	
	private CreateProjectFrame parentFrame;
	private JButton indietroButton;
	private JButton fineButton;
	private JButton annullaButton;
	
	public ProgettoGenericoPanel(Dimension dimension, CreateProjectFrame parentFrame) {

		this.parentFrame = parentFrame;
		this.setPreferredSize(dimension);
		this.setBackground(Color.cyan);
		
		JPanel buttonsPanel = new JPanel();
		
		indietroButton = new JButton("< Indietro");
		indietroButton.addActionListener(this);
		
		fineButton = new JButton("Fine");
		fineButton.addActionListener(this);
		
		annullaButton = new JButton("Annulla");
		annullaButton.addActionListener(this);
	
		buttonsPanel.add(annullaButton);
		buttonsPanel.add(indietroButton);
		buttonsPanel.add(fineButton);
		
		JLabel test = new JLabel("Progetto generico");
		
		this.add(test);
		this.add(buttonsPanel);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == indietroButton) {
			
			parentFrame.showPanel("SELEZIONE");
			
		}
		
		if(e.getSource() == annullaButton) {
			
			parentFrame.dispose();
			
		}
		
	}
	
}
