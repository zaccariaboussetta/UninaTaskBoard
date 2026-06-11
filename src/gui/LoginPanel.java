package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class LoginPanel extends JPanel{
	
	private WindowApp windowApplication;
	private JButton loginButton;
	private JButton createAccountButton;
	private JTextField emailTextField;
	private JTextField passwordTextField;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	
	public LoginPanel(WindowApp windowApp) {
		this.windowApplication = windowApp;
		
		this.setBackground(Color.BLUE);
		
		emailLabel = new JLabel("e-mail istituzionale");
		emailTextField = new JTextField();
		passwordLabel = new JLabel("password");
		passwordTextField = new JTextField();
		loginButton = new JButton();
		createAccountButton = new JButton();
		
		this.add(emailLabel);
		this.add(emailTextField);
		this.add(passwordLabel);
		this.add(passwordTextField);
		this.add(loginButton);
		this.add(createAccountButton);
	}
}
