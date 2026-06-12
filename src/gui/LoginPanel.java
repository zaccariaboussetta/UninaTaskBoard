package gui;

import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel{
	
	private WindowApp windowApplication;
	private JButton loginButton;
	private JButton createAccountButton;
	private JTextField emailTextField;
	private JTextField passwordTextField;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JLabel logoUniLabel;
	
	public LoginPanel(WindowApp windowApp) {
		this.windowApplication = windowApp;
		ImageIcon logoUni = new ImageIcon("src/logoUni.png");
		
		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		
		JPanel innerLoginPanel = new JPanel();
		innerLoginPanel.setBackground(Color.black);
		
		
		
		logoUniLabel = new JLabel();
		logoUniLabel.setIcon(logoUni);
		emailLabel = new JLabel("e-mail istituzionale");
		emailTextField = new JTextField();
		passwordLabel = new JLabel("password");
		passwordTextField = new JTextField();
		loginButton = new JButton();
		createAccountButton = new JButton();
		
		innerLoginPanel.add(logoUniLabel);
		innerLoginPanel.add(emailLabel);
		innerLoginPanel.add(emailTextField);
		innerLoginPanel.add(passwordLabel);
		innerLoginPanel.add(passwordTextField);
		innerLoginPanel.add(loginButton);
		innerLoginPanel.add(createAccountButton);
		
		this.add(innerLoginPanel);
	}
}
