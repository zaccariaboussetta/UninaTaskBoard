package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

public class StyledButton extends JButton{
	
	public StyledButton(String buttonText) {
		super(buttonText);
		this.setBackground(new Color(0, 122, 255)); //Colore sfondo bottone
		this.setForeground(Color.WHITE); //Colore testo
		this.setFont(new Font("Arial", Font.BOLD, 14));
		this.setFocusPainted(false); //Rimuove il quadratino trattegiato intorno al testo del bottone
		this.setBorderPainted(false); //Rimuove il bordo nativo 3D del bottone
		this.setOpaque(true);  
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
