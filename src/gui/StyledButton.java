package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

public class StyledButton extends JButton{
	
	public StyledButton(String buttonText) {
		super(buttonText);
		this.setBackground(new Color(0, 122, 255));
		this.setForeground(Color.WHITE);
		this.setFont(new Font("Arial", Font.BOLD, 14));
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setOpaque(true);  
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
}
