package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends JPanel implements ActionListener{
	
    private WindowApp windowApplication;
    private JTextField emailTextField;
    private JPasswordField passwordTextField; 
    private JButton loginButton;
    private JButton createAccountButton;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel logoUniLabel;
    private JLabel titoloLabel;
	
    public LoginPanel(WindowApp windowApp) {
        this.windowApplication = windowApp;
		
        
        this.setBackground(new Color(0xBDCAF2)); 
        this.setLayout(new GridBagLayout()); 
		
        
        JPanel innerLoginPanel = new JPanel();
        innerLoginPanel.setBackground(Color.WHITE);
        innerLoginPanel.setPreferredSize(new Dimension(600, 800));
        //innerLoginPanel.setBorder(new EmptyBorder(30, 40, 30, 40)); 
        innerLoginPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
        innerLoginPanel.setLayout(new BoxLayout(innerLoginPanel, BoxLayout.Y_AXIS));
		
        
        logoUniLabel = new LogoImageLabel();
        logoUniLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titoloLabel = new JLabel("Welcome to Unina Task Board");
        titoloLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titoloLabel.setAlignmentX(CENTER_ALIGNMENT);
		
        emailLabel = new JLabel("E-mail istituzionale");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        emailTextField = new JTextField(20); 
        emailTextField.setMaximumSize(new Dimension(250, 30)); 
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        passwordTextField = new JPasswordField(20);
        passwordTextField.setMaximumSize(new Dimension(250, 30));
        
        loginButton = new StyledButton("Accedi");
        loginButton.addActionListener(this);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
		createAccountButton = new StyledButton("Crea Account");
        createAccountButton.addActionListener(this);
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel orLabel = new JLabel("o");
        orLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerLoginPanel.add(logoUniLabel);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 50))); 
        
        innerLoginPanel.add(titoloLabel);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        
        innerLoginPanel.add(emailLabel);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerLoginPanel.add(emailTextField);
        
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 15))); 
        
        innerLoginPanel.add(passwordLabel);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerLoginPanel.add(passwordTextField);
        
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 50))); 
        
        innerLoginPanel.add(loginButton);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        innerLoginPanel.add(orLabel);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        innerLoginPanel.add(createAccountButton);
		
        
        this.add(innerLoginPanel);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			//TODO: Qui va chiamato il controller di autenticazione : se esiste un utente, restituisce true e passa alla dashboard
			windowApplication.showPanel("WELCOME");
		}
		if(e.getSource() == createAccountButton) {
			windowApplication.showPanel("CREATE");
		}
		
	}
}