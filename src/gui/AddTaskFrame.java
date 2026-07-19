package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.*;

import controllers.DashboardController;
import controllers.SessionController;
import entities.*;

public class AddTaskFrame extends JDialog implements ActionListener {

	private DashboardController dashboardController;
	private DashboardPanel dashboardPanel;
	
	private JComboBox<String> tipoCombo;
	private StyledTextField descrizioneField;
	private StyledTextField scadenzaField;
	private JComboBox<String> assegnatarioCombo;
	
	
	private JComboBox<String> tipoSviluppoCombo;
	private StyledTextField linguaggioField;
	private StyledTextField branchField;
	
	
	private StyledTextField titoloDocuField;
	private StyledTextField formatoField;
	private StyledTextField sezioneField;
	private StyledTextField linkField;
	
	private JPanel cardsPanel;
	private CardLayout cardLayout;
	
	private ErrorLabel errorLabel;
	private JButton salvaButton;
	private JButton annullaButton;

	public AddTaskFrame(MainWindow mainWindow, DashboardController dc, DashboardPanel dp) {
		super(mainWindow, "Nuova Attività", true); 
		this.dashboardController = dc;
		this.dashboardPanel = dp;
		
		this.setSize(450, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(mainWindow);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.WHITE);
		
		JLabel titoloLabel = new JLabel("Crea Nuova Task");
		titoloLabel.setFont(new Font("Arial", Font.BOLD, 22));
		titoloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		descrizioneField = new StyledTextField(20);
		descrizioneField.setMaximumSize(new Dimension(280, 30));
		
		scadenzaField = new StyledTextField(20);
		scadenzaField.setMaximumSize(new Dimension(280, 30));
		
		tipoCombo = new JComboBox<>(new String[]{"Sviluppo", "Documentazione"});
		tipoCombo.setMaximumSize(new Dimension(280, 30));
		tipoCombo.setBackground(Color.WHITE);
		
		assegnatarioCombo = new JComboBox<>();
		assegnatarioCombo.setMaximumSize(new Dimension(280, 30));
		assegnatarioCombo.setBackground(Color.WHITE);
		assegnatarioCombo.addItem("Nessuno (Non assegnata)");
		
		for (Membro m : dashboardController.getListaMembri()) {
			assegnatarioCombo.addItem(m.getUtente().getNome() + " " + m.getUtente().getCognome());
		}
		
		
		cardLayout = new CardLayout();
		cardsPanel = new JPanel(cardLayout);
		cardsPanel.setBackground(Color.WHITE);
		cardsPanel.setMaximumSize(new Dimension(450, 220));
		
		
		JPanel sviluppoPanel = new JPanel();
		sviluppoPanel.setLayout(new BoxLayout(sviluppoPanel, BoxLayout.Y_AXIS));
		sviluppoPanel.setBackground(Color.WHITE);
		
		tipoSviluppoCombo = new JComboBox<>(new String[]{"Scrittura", "Modifica"});
		tipoSviluppoCombo.setMaximumSize(new Dimension(280, 30));
		tipoSviluppoCombo.setBackground(Color.WHITE);
		linguaggioField = new StyledTextField(20);
		linguaggioField.setMaximumSize(new Dimension(280, 30));
		branchField = new StyledTextField(20);
		branchField.setMaximumSize(new Dimension(280, 30));
		
		sviluppoPanel.add(creaLabelCentrata("Tipologia Sviluppo"));
		sviluppoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		sviluppoPanel.add(tipoSviluppoCombo);
		sviluppoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		sviluppoPanel.add(creaLabelCentrata("Linguaggio (es. Java)"));
		sviluppoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		sviluppoPanel.add(linguaggioField);
		sviluppoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		sviluppoPanel.add(creaLabelCentrata("Nome Branch"));
		sviluppoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		sviluppoPanel.add(branchField);
		
		
		JPanel docuPanel = new JPanel();
		docuPanel.setLayout(new BoxLayout(docuPanel, BoxLayout.Y_AXIS));
		docuPanel.setBackground(Color.WHITE);
		
		titoloDocuField = new StyledTextField(20);
		titoloDocuField.setMaximumSize(new Dimension(280, 30));
		formatoField = new StyledTextField(20);
		formatoField.setMaximumSize(new Dimension(280, 30));
		sezioneField = new StyledTextField(20);
		sezioneField.setMaximumSize(new Dimension(280, 30));
		linkField = new StyledTextField(20);
		linkField.setMaximumSize(new Dimension(280, 30));
		
		docuPanel.add(creaLabelCentrata("Titolo Documento"));
		docuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		docuPanel.add(titoloDocuField);
		docuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		docuPanel.add(creaLabelCentrata("Formato (es. PDF)"));
		docuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		docuPanel.add(formatoField);
		docuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		docuPanel.add(creaLabelCentrata("Sezione Riferimento"));
		docuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		docuPanel.add(sezioneField);
		docuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		docuPanel.add(creaLabelCentrata("Link Risorsa"));
		docuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		docuPanel.add(linkField);
		
		cardsPanel.add(sviluppoPanel, "Sviluppo");
		cardsPanel.add(docuPanel, "Documentazione");
		
		
		tipoCombo.addActionListener(e -> cardLayout.show(cardsPanel, (String) tipoCombo.getSelectedItem()));
		
		
		errorLabel = new ErrorLabel();
		errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		salvaButton = new JButton("Salva Task");
		salvaButton.addActionListener(this);
		
		annullaButton = new JButton("Annulla");
		annullaButton.addActionListener(e -> this.dispose());
		
		JPanel bottoniPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottoniPanel.setBackground(Color.WHITE);
		bottoniPanel.add(annullaButton);
		bottoniPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		bottoniPanel.add(salvaButton);
		
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		mainPanel.add(titoloLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		mainPanel.add(creaLabelCentrata("Tipo Attività"));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPanel.add(tipoCombo);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		mainPanel.add(creaLabelCentrata("Descrizione Breve"));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPanel.add(descrizioneField);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		mainPanel.add(creaLabelCentrata("Scadenza (GG/MM/AAAA)"));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPanel.add(scadenzaField);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		mainPanel.add(creaLabelCentrata("Assegna a Membro"));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPanel.add(assegnatarioCombo);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		mainPanel.add(cardsPanel); 
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(errorLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(bottoniPanel);
		
		this.add(mainPanel);
		this.setVisible(true); 
	}
	
	
	private JLabel creaLabelCentrata(String testo) {
		JLabel label = new JLabel(testo);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		return label;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == salvaButton) {
			try {
				errorLabel.setText("");
				
				String desc = descrizioneField.getText();
				if (desc.isBlank()) throw new Exception("La descrizione non può essere vuota.");
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate scadenza = LocalDate.parse(scadenzaField.getText(), dtf);
				
				Progetto progetto = SessionController.getInstance().getCorrenteProgetto();
				Attivita nuovaAttivita = null;
				String tipoSelezionato = (String) tipoCombo.getSelectedItem();
				
				if (tipoSelezionato.equals("Sviluppo")) {
					nuovaAttivita = new AttivitaSviluppo(desc, scadenza, 
							(String) tipoSviluppoCombo.getSelectedItem(), 
							linguaggioField.getText(), branchField.getText(), progetto);
				} else {
					nuovaAttivita = new AttivitaDocumentazione(desc, scadenza, 
							titoloDocuField.getText(), formatoField.getText(), 
							sezioneField.getText(), linkField.getText(), progetto);
				}
				
				
				Membro assegnatario = null;
				int indiceMembro = assegnatarioCombo.getSelectedIndex();
				if (indiceMembro > 0) { 
					
					assegnatario = dashboardController.getListaMembri().get(indiceMembro - 1);
				}
				
				
				boolean success = dashboardController.getTaskController().inserisciNuovaAttivita(nuovaAttivita, progetto, assegnatario);
				
				if (success) {
					dashboardPanel.update(); 
					this.dispose(); 
				} else {
					errorLabel.setText("Errore interno nel database.");
				}
				
			} catch (DateTimeParseException dtex) {
				errorLabel.setText("Formato scadenza errato (GG/MM/AAAA)");
			} catch (Exception ex) {
				errorLabel.setText(ex.getMessage());
			}
		}
	}
}