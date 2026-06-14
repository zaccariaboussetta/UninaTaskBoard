package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controllers.AuthController;

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
	private Image backgroundImage;
	private JTextArea descrizioneTextArea;
	private JLabel errorLabel;
	private AuthController authController;
    
    public LoginPanel(WindowApp windowApp, AuthController auth) {
        this.windowApplication = windowApp;
		this.authController = auth;
        
        this.setBackground(new Color(0xBDCAF2)); 
        this.setLayout(new GridBagLayout()); 
		
        backgroundImage = new ImageIcon("src/background.jpg").getImage();
        
        JPanel innerLoginPanel = new JPanel();
        innerLoginPanel.setBackground(new Color(255, 255, 255));
        innerLoginPanel.setPreferredSize(new Dimension(600, 800));
        innerLoginPanel.setMinimumSize(new Dimension(600, 800));
        //innerLoginPanel.setBorder(new EmptyBorder(30, 40, 30, 40)); 
        innerLoginPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
        innerLoginPanel.setLayout(new BoxLayout(innerLoginPanel, BoxLayout.Y_AXIS));
		
        
        logoUniLabel = new LogoImageLabel();
        logoUniLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titoloLabel = new JLabel("Benvenuta o benvenuto ad Unina Task Board.");
        titoloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titoloLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        descrizioneTextArea = new JTextArea(
        		"Unina Task Board è l'applicativo nato da un'esigenza semplice: sopravvivere ai progetti di gruppo. "
        				+ "Creato da studenti per studenti, ti permette di organizzare il lavoro, monitorare le scadenze e aggiungere "
        				+ "collaboratori (aka i colleghi con cui dovrai dividere l'ansia e il codice). "
        				+ "Include insight, gestione dei task e tutto quel jazz che ti aspetteresti "
        				+ "dall'ennesimo clone di un software di management, ma fatto su misura per noi.");
        descrizioneTextArea.setLineWrap(true);      
        descrizioneTextArea.setWrapStyleWord(true);
        descrizioneTextArea.setEditable(false);     
        descrizioneTextArea.setOpaque(false);        
        descrizioneTextArea.setFocusable(false);     
        descrizioneTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descrizioneTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descrizioneTextArea.setMaximumSize(new Dimension(450, 150));
		
        emailLabel = new JLabel("E-mail istituzionale");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        emailTextField = new StyledTextField(20); 
        emailTextField.setMaximumSize(new Dimension(250, 30)); 
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        passwordTextField = new StyledPasswordField(20);
        passwordTextField.setMaximumSize(new Dimension(250, 30));
        
        errorLabel = new ErrorLabel();
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
        
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
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        innerLoginPanel.add(descrizioneTextArea);
        //innerLoginPanel.add(Box.createRigidArea(new Dimension(0,50)));
        
        innerLoginPanel.add(emailLabel);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerLoginPanel.add(emailTextField);
        
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 15))); 
        
        innerLoginPanel.add(passwordLabel);
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerLoginPanel.add(passwordTextField);
        
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        
        innerLoginPanel.add(errorLabel);
        
        innerLoginPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        
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
			
			errorLabel.setText("");
		 	String email = emailTextField.getText();
			  
		 	char[] passwordArray = passwordTextField.getPassword();
		 	String password = String.valueOf(passwordArray);
			  
		 	if(password.isBlank() || email.isBlank()) {
			  
		 		errorLabel.setText("Riempire tutti i campi!");
			  
		  	}
			  
		 	else {
			 
	 		if(authController.authenticationLogin("z.boussetta@studenti.unina.it", "helloworld")) { //TODO: Cambiare parametri dopo il testing.
	 			
	 			WelcomePanel welcomePanel = new WelcomePanel(windowApplication);
	 			windowApplication.addPanel(welcomePanel, "WELCOME");
	 			windowApplication.showPanel("WELCOME"); 
			
	 		} 
	 		else {
				  
	 			errorLabel.setText("E-mail o password errata."); }
			  
	  		}
			  
		}
		
		if(e.getSource() == createAccountButton) {
			errorLabel.setText("");
			windowApplication.showPanel("CREATE");
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}