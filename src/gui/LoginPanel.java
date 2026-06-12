package gui;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends JPanel {
	
    private WindowApp windowApplication;
    private JTextField emailTextField;
    private JPasswordField passwordTextField; 
    private JButton loginButton;
    private JButton createAccountButton;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel logoUniLabel;
	
    public LoginPanel(WindowApp windowApp) {
        this.windowApplication = windowApp;
		
        
        this.setBackground(new Color(240, 240, 240)); 
        this.setLayout(new GridBagLayout()); 
		
        
        JPanel innerLoginPanel = new JPanel();
        innerLoginPanel.setBackground(Color.WHITE);
        
        innerLoginPanel.setPreferredSize(new Dimension(600, 500));
        innerLoginPanel.setMinimumSize(new Dimension(450, 350));
        innerLoginPanel.setMaximumSize(new Dimension(800, 700));
        
        innerLoginPanel.setBorder(new EmptyBorder(30, 40, 30, 40)); 
        
        innerLoginPanel.setLayout(new BoxLayout(innerLoginPanel, BoxLayout.Y_AXIS));
		
        
        logoUniLabel = new JLabel();
        ImageIcon logoUniIcon = new ImageIcon("src/uniLogo.png");
        Image scaledLogo = logoUniIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logoUniLabel.setIcon(new ImageIcon (scaledLogo));
        
        logoUniLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
        emailLabel = new JLabel("E-mail istituzionale");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        emailTextField = new JTextField(20); 
        emailTextField.setMaximumSize(new Dimension(250, 30)); 
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        passwordTextField = new JPasswordField(20);
        passwordTextField.setMaximumSize(new Dimension(250, 30));
        
        loginButton = new JButton("Accedi");
     
        loginButton.setBackground(new Color(0, 122, 255)); 
        loginButton.setForeground(Color.WHITE); 
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        loginButton.setFocusPainted(false);  
        loginButton.setBorderPainted(false); 
        loginButton.setOpaque(true);         
        
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        createAccountButton = new JButton("Crea Account");
        
        createAccountButton.setBackground(new Color(0, 122, 255));
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        createAccountButton.setFocusPainted(false);
        createAccountButton.setBorderPainted(false);
        createAccountButton.setOpaque(true);
        
        createAccountButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
       
        innerLoginPanel.add(logoUniLabel);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 100))); 
        
        innerLoginPanel.add(emailLabel);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerLoginPanel.add(emailTextField);
        
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 15))); 
        
        innerLoginPanel.add(passwordLabel);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerLoginPanel.add(passwordTextField);
        
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 25))); 
        
        innerLoginPanel.add(loginButton);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        innerLoginPanel.add(createAccountButton);
		
        
        this.add(innerLoginPanel);
    }
}