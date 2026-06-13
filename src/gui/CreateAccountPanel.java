package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controllers.RegistrazioneUtenteController;
import exceptions.EmailException;
import exceptions.MatricolaException;
import exceptions.PasswordException;
import exceptions.RegistrationException;

public class CreateAccountPanel extends JPanel implements ActionListener {

    private WindowApp windowApplication;
    private JLabel titoloLabel;
    private JLabel nomeLabel;
    private JTextField nomeTextField;
    private JLabel cognomeLabel;
    private JTextField cognomeTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel matricolaLabel;
    private JTextField matricolaTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordTextField;
    private JLabel confermaPasswordLabel;
    private JPasswordField confermaPasswordTextField;
    private JLabel confermaLabel;
    private JButton confermaButton;
    private JLabel cancellaLabel;
    private JButton cancellaButton;
    private JLabel logoUniLabel;
    private Image backgroundImage;
    private JLabel errorLabel;
    private RegistrazioneUtenteController regUtenteController;

    public CreateAccountPanel(WindowApp windowApp, RegistrazioneUtenteController registrazioneUtenteControl) {
        this.windowApplication = windowApp;
        this.regUtenteController = registrazioneUtenteControl;
       
        this.setBackground(new Color(0xBDCAF2));
        this.setLayout(new GridBagLayout()); 
        backgroundImage = new ImageIcon("src/background2.jpg").getImage();
        
        JPanel innerCreatePanel = new JPanel();
        innerCreatePanel.setBackground(Color.WHITE);
        innerCreatePanel.setPreferredSize(new Dimension(600, 800));
        innerCreatePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        innerCreatePanel.setLayout(new BoxLayout(innerCreatePanel, BoxLayout.Y_AXIS));

        logoUniLabel = new LogoImageLabel();
        logoUniLabel.setAlignmentX(CENTER_ALIGNMENT);;
        
        titoloLabel = new JLabel("Registrazione Studente");
        titoloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titoloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nomeLabel = new JLabel("Nome");
        nomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomeTextField = new StyledTextField(20);
        nomeTextField.setMaximumSize(new Dimension(250, 30));

        cognomeLabel = new JLabel("Cognome");
        cognomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cognomeTextField = new StyledTextField(20);
        cognomeTextField.setMaximumSize(new Dimension(250, 30));

        emailLabel = new JLabel("E-mail istituzionale");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailTextField = new StyledTextField(20);
        emailTextField.setMaximumSize(new Dimension(250, 30));

        matricolaLabel = new JLabel("Matricola studente");
        matricolaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        matricolaTextField = new StyledTextField(20);
        matricolaTextField.setMaximumSize(new Dimension(250, 30));

        passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordTextField = new StyledPasswordField(20);
        passwordTextField.setMaximumSize(new Dimension(250, 30));

        confermaPasswordLabel = new JLabel("Conferma Password");
        confermaPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        confermaPasswordTextField = new StyledPasswordField(20);
        confermaPasswordTextField.setMaximumSize(new Dimension(250, 30));
        
        errorLabel = new ErrorLabel();
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        confermaButton = new StyledButton("Conferma");
        confermaButton.addActionListener(this);
        
        cancellaButton = new StyledButton("Cancella");
        cancellaButton.addActionListener(this);
        
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE); 
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS)); 
        buttonsPanel.add(cancellaButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(40, 0))); 
        buttonsPanel.add(confermaButton);

        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerCreatePanel.add(logoUniLabel);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0,20)));
        
        innerCreatePanel.add(titoloLabel);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 30)));

        innerCreatePanel.add(nomeLabel);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerCreatePanel.add(nomeTextField);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 15)));

        innerCreatePanel.add(cognomeLabel);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerCreatePanel.add(cognomeTextField);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 15)));

        innerCreatePanel.add(emailLabel);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerCreatePanel.add(emailTextField);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 15)));

        innerCreatePanel.add(matricolaLabel);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerCreatePanel.add(matricolaTextField);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 15)));

        innerCreatePanel.add(passwordLabel);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerCreatePanel.add(passwordTextField);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 15)));

        innerCreatePanel.add(confermaPasswordLabel);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerCreatePanel.add(confermaPasswordTextField);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        
        innerCreatePanel.add(errorLabel);
        innerCreatePanel.add(Box.createRigidArea(new Dimension(0,10)));

        innerCreatePanel.add(buttonsPanel);

        
        this.add(innerCreatePanel);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confermaButton) {
			//TODO: Va chiamato il controller che verificherà che i campi siano corretti, creerà l'user  nel db e restituirà TRUE;
			
			String nome = nomeTextField.getText();
			String cognome = cognomeTextField.getText();
			String email = emailTextField.getText();
			String matricola = matricolaTextField.getText();
			char[] passwordArray = passwordTextField.getPassword();
			String password = String.valueOf(passwordArray);
			char[] passwordDiConfermaArray = confermaPasswordTextField.getPassword();
			String passwordDiConferma = String.valueOf(passwordDiConfermaArray);
			
			
			if(
				nome == null || 
				cognome == null|| 
				email == null|| 
				matricola == null|| 
				password == null || 
				passwordDiConferma == null) 
			{
				
				errorLabel.setText("Compilare tutti i campi.");
				
			}
			else {
				try {
					regUtenteController.verifyValidPassword(password, passwordDiConferma);
					regUtenteController.verifyValidEMail(email);
					regUtenteController.verifyValidMatricola(matricola);
					regUtenteController.registraUtente(nome, cognome, email, matricola, password);
					windowApplication.showPanel("WELCOME");
				}
				catch(PasswordException pex) {
					errorLabel.setText(pex.getMessage());
				}
				catch(EmailException eex) {
					errorLabel.setText(eex.getMessage());
				}
				catch(MatricolaException mex) {
					errorLabel.setText(mex.getMessage());
				} catch (RegistrationException rex) {
					errorLabel.setText(rex.getMessage());
				}
				
			}
			
			
		}
		if(e.getSource() == cancellaButton) {
			windowApplication.showPanel("LOGIN");
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}