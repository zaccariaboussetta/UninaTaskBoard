package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controllers.AuthController;

public class LoginPanel extends JPanel implements ActionListener{
	
    private MainWindow mainWindow;
    private JTextField emailTextField;
    private JPasswordField passwordTextField; 
    private JButton loginButton;
    private JButton createAccountButton;
    private JLabel logoUniLabel;
	private Image backgroundImage;
	private JLabel errorLabel;
	private AuthController authController;
    
    public LoginPanel(MainWindow mainWindow, AuthController auth) {
    	
        this.mainWindow = mainWindow;
		this.authController = auth;
		
        this.setLayout(new GridLayout(1, 2)); 
		
        Dimension dimension = new Dimension(450,600);
        mainWindow.setResizable(false);
        
        //Costruzione del pannello di destra
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
        //Costruzione del pannello dei textfield per il login
        JPanel areaLoginPanel = new JPanel();
        areaLoginPanel.setBackground(new Color(255, 255, 255));
        areaLoginPanel.setPreferredSize(dimension);
        areaLoginPanel.setMinimumSize(dimension);
        areaLoginPanel.setMaximumSize(dimension);
        areaLoginPanel.setLayout(new BoxLayout(areaLoginPanel, BoxLayout.Y_AXIS));
        logoUniLabel = new LogoImageLabel();
        logoUniLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titoloLabel = new JLabel("Benvenuta/o ad Unina Task Board");
        titoloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titoloLabel.setAlignmentX(CENTER_ALIGNMENT);
		
        JLabel emailLabel = new JLabel("E-mail istituzionale");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailTextField = new StyledTextField(20); 
        emailTextField.setMaximumSize(new Dimension(250, 30)); 
        
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordTextField = new StyledPasswordField(20);
        passwordTextField.setMaximumSize(new Dimension(250, 30));
        
        errorLabel = new ErrorLabel();
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
         
        //Pannello dei bottoni
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
        buttonsPanel.setBackground(Color.white);
        
        loginButton = new JButton("Accedi");
        loginButton.addActionListener(this);
        
		createAccountButton = new JButton("Crea Account");
        createAccountButton.addActionListener(this);
        
        buttonsPanel.add(createAccountButton);
        buttonsPanel.add(loginButton);
        
        //Costruzione pannello interno di sinistra
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(dimension);
        leftPanel.setMaximumSize(dimension);
        leftPanel.setMinimumSize(dimension);
        leftPanel.setBackground(Color.black);
        
        
       //Costruzione della GUI 
        
        areaLoginPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        areaLoginPanel.add(logoUniLabel);
        areaLoginPanel.add(Box.createRigidArea(new Dimension(0, 50))); 
        
        areaLoginPanel.add(titoloLabel);
        areaLoginPanel.add(Box.createRigidArea(new Dimension(0, 25)));
   
        
        areaLoginPanel.add(emailLabel);
        areaLoginPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        areaLoginPanel.add(emailTextField);
        
        areaLoginPanel.add(Box.createRigidArea(new Dimension(0, 15))); 
        
        areaLoginPanel.add(passwordLabel);
        areaLoginPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        areaLoginPanel.add(passwordTextField);
        
        areaLoginPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        
        areaLoginPanel.add(errorLabel);
        
        rightPanel.add(areaLoginPanel, BorderLayout.CENTER);
        rightPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
        this.add(leftPanel);
        this.add(rightPanel);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			try {
				errorLabel.setText("");
				String email = emailTextField.getText();
				char[] passwordArray = passwordTextField.getPassword();
				String password = String.valueOf(passwordArray);
				email = "s.connor@studenti.unina.it";
				password = "Skynet1997!!";
				authController.authenticationLogin(email, password);
				
				((ProgettiPanel)mainWindow.getPanelByName("PROGETTI")).updateOnLogin();
				
				mainWindow.showPanel("PROGETTI");
	
			} 
			catch (Exception e1) { errorLabel.setText(e1.getMessage()); }
		 			
		}
		
		if(e.getSource() == createAccountButton) {
			
			errorLabel.setText("");
			mainWindow.showPanel("CREATE");
			
		}
		
	}
	
}
