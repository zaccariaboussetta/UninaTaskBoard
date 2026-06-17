package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JRadioButton;

public class SelezioneTipologiaProgettoPanel extends JPanel implements ActionListener{
	
	private CreateProjectFrame parentFrame;
	private JButton avantiButton;
	private JButton annullaButton;
	private JCheckBox checkSviluppo;
	private JCheckBox checkGenerico;
	private JCheckBox checkEsame;
	
	
	public SelezioneTipologiaProgettoPanel(Dimension dimension, CreateProjectFrame parentFrame) {
		
		this.parentFrame = parentFrame;
		this.setPreferredSize(dimension);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel leftPanel1 = new JPanel();
		leftPanel1.setPreferredSize(new Dimension(400, dimension.height));
		leftPanel1.setLayout(new BorderLayout());
		
		ImageIcon imageLeft = new ImageIcon("src/leftPanelIcon.jpg");
		Image scaledImage = imageLeft.getImage().getScaledInstance(400, dimension.height, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
		
		leftPanel1.add(imageLabel);
		
		JPanel rightPanel1 = new JPanel();
		rightPanel1.setPreferredSize(new Dimension(400, HEIGHT));
		rightPanel1.setBackground(Color.white);
		rightPanel1.setLayout(new BorderLayout());
		
		JLabel titoloLabel = new JLabel("Selezionare tipologia di progetto");
		
		
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
		
		checkGenerico = new JCheckBox("Progetto - generico");
		checkGenerico.setAlignmentX(LEFT_ALIGNMENT);
		
		checkSviluppo = new JCheckBox("Progetto - sviluppo applicativo");
		checkSviluppo.setAlignmentX(LEFT_ALIGNMENT);
		
		checkEsame = new JCheckBox("Progetto - preparazione esame");
		checkEsame.setAlignmentX(LEFT_ALIGNMENT);
		
		checkBoxPanel.add(checkGenerico);
		checkBoxPanel.add(checkEsame);
		checkBoxPanel.add(checkSviluppo);
		
		//Bottoni
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		avantiButton = new JButton("Avanti >");
		avantiButton.addActionListener(this);
		
		annullaButton = new JButton("Annulla");
		annullaButton.addActionListener(this);
	
		buttonsPanel.add(annullaButton);
		buttonsPanel.add(avantiButton);
		
		rightPanel1.add(titoloLabel, BorderLayout.NORTH);
		rightPanel1.add(checkBoxPanel,BorderLayout.CENTER);
		rightPanel1.add(buttonsPanel, BorderLayout.SOUTH);
		
		this.add(leftPanel1);
		this.add(rightPanel1);
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
				//TODO:
			}
			
		}
		
		if(e.getSource() == annullaButton) {
			
			parentFrame.dispose();
			
		}
		
	}

}
