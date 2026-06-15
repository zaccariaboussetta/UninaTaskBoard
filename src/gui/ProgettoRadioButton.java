package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class ProgettoRadioButton extends JRadioButton{

	public ProgettoRadioButton(String nomeProgetto, ImageIcon folderIcon) {
		
		super(nomeProgetto, folderIcon);
		
		
		this.setMargin(new Insets(10, 10, 10, 10));
		
		this.setFont(new Font("Arial", Font.PLAIN, 14)); 
		this.setForeground(Color.BLUE);                 
		this.setBackground(Color.WHITE);                
		this.setFocusPainted(false);                    
		this.setOpaque(false);
		
	}
	
}
