package gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LogoImageLabel extends JLabel{
	public LogoImageLabel() {
		ImageIcon logoUniIcon = new ImageIcon("src/uniLogo.png");
        Image scaledLogo = logoUniIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon (scaledLogo));
	}
}
