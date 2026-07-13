package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entities.Attivita;
import entities.AttivitaDocumentazione;
import entities.AttivitaSviluppo;
import entities.Membro;
import entities.Progetto;

public class AggiungiTaskDialog extends JDialog {

	private JTextField descrizioneField;
	private JTextField scadenzaField;
	private JComboBox<String> tipologiaCombo;
	private JComboBox<String> assegnatarioCombo;
	
	private JPanel specificFieldsPanel;
	private CardLayout cardLayout;
	
	private JComboBox<String> tipologiaSviluppoCombo;
	private JTextField linguaggioField;
	private JTextField branchField;
	
	private JTextField titoloDocuField;
	private JTextField formatoField;
	private JTextField sezioneField;
	private JTextField linkField;
	
	private ArrayList<Membro> teamMembers;
	private boolean confermato = false;
	private Attivita nuovaAttivita = null;
	private Membro membroAssegnato = null;

	public AggiungiTaskDialog(JFrame parent, Progetto progetto, ArrayList<Membro> membri) {
		super(parent, "Nuova Attività", true);
		
		if (membri == null) {
			membri = new ArrayList<>();
		}
		
		this.teamMembers = membri;
		
		this.setSize(450, 420);
		this.setLocationRelativeTo(parent);
		this.setLayout(new BorderLayout(10, 10));
		
		JPanel mainPanel = new JPanel(new BorderLayout(0, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		JPanel commonFieldsPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		
		descrizioneField = new JTextField();
		scadenzaField = new JTextField(LocalDate.now().plusDays(7).toString());
		
		String[] membriNomi = new String[membri.size() + 1];
		membriNomi[0] = "Nessuno (Non assegnata)";
		for (int i = 0; i < membri.size(); i++) {
			Membro m = membri.get(i);
			membriNomi[i+1] = m.getUtente().getNome() + " " + m.getUtente().getCognome();
		}
		assegnatarioCombo = new JComboBox<>(membriNomi);
		
		tipologiaCombo = new JComboBox<>(new String[]{"Sviluppo", "Documentazione"});
		
		commonFieldsPanel.add(new JLabel("Descrizione:"));
		commonFieldsPanel.add(descrizioneField);
		commonFieldsPanel.add(new JLabel("Scadenza (YYYY-MM-DD):"));
		commonFieldsPanel.add(scadenzaField);
		commonFieldsPanel.add(new JLabel("Assegna a:"));
		commonFieldsPanel.add(assegnatarioCombo);
		commonFieldsPanel.add(new JLabel("Tipo Attività:"));
		commonFieldsPanel.add(tipologiaCombo);
		
		cardLayout = new CardLayout();
		specificFieldsPanel = new JPanel(cardLayout);
		
		JPanel sviluppoPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		tipologiaSviluppoCombo = new JComboBox<>(new String[]{"Scrittura", "Modifica"});
		linguaggioField = new JTextField();
		branchField = new JTextField();
		
		sviluppoPanel.add(new JLabel("Tipo Sviluppo:"));
		sviluppoPanel.add(tipologiaSviluppoCombo);
		sviluppoPanel.add(new JLabel("Linguaggio:"));
		sviluppoPanel.add(linguaggioField);
		sviluppoPanel.add(new JLabel("Branch:"));
		sviluppoPanel.add(branchField);
		
		JPanel docuPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		titoloDocuField = new JTextField();
		formatoField = new JTextField();
		sezioneField = new JTextField();
		linkField = new JTextField();
		
		docuPanel.add(new JLabel("Titolo Documento:"));
		docuPanel.add(titoloDocuField);
		docuPanel.add(new JLabel("Formato:"));
		docuPanel.add(formatoField);
		docuPanel.add(new JLabel("Sezione:"));
		docuPanel.add(sezioneField);
		docuPanel.add(new JLabel("Link Risorsa:"));
		docuPanel.add(linkField);
		
		specificFieldsPanel.add(sviluppoPanel, "Sviluppo");
		specificFieldsPanel.add(docuPanel, "Documentazione");
		
		tipologiaCombo.addActionListener(e -> cardLayout.show(specificFieldsPanel, (String) tipologiaCombo.getSelectedItem()));
		
		mainPanel.add(commonFieldsPanel, BorderLayout.NORTH);
		mainPanel.add(specificFieldsPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton salvaButton = new JButton("Salva");
		JButton annullaButton = new JButton("Annulla");
		
		salvaButton.addActionListener(e -> {
			try {
				String desc = descrizioneField.getText();
				LocalDate scadenza = LocalDate.parse(scadenzaField.getText());
				
				if (tipologiaCombo.getSelectedItem().equals("Sviluppo")) {
					nuovaAttivita = new AttivitaSviluppo(
						desc, scadenza, 
						(String) tipologiaSviluppoCombo.getSelectedItem(),
						linguaggioField.getText(),
						branchField.getText(),
						progetto
					);
				} else {
					nuovaAttivita = new AttivitaDocumentazione(
						desc, scadenza,
						titoloDocuField.getText(),
						formatoField.getText(),
						sezioneField.getText(),
						linkField.getText(),
						progetto
					);
				}
				
				int selectedIndex = assegnatarioCombo.getSelectedIndex();
				if (selectedIndex > 0) {
					membroAssegnato = teamMembers.get(selectedIndex - 1);
				}
				
				confermato = true;
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Errore nei dati inseriti. Formato data non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		annullaButton.addActionListener(e -> dispose());
		
		buttonPanel.add(salvaButton);
		buttonPanel.add(annullaButton);
		
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public boolean isConfermato() {
		return confermato;
	}
	
	public Attivita getNuovaAttivita() {
		return nuovaAttivita;
	}
	
	public Membro getMembroAssegnato() {
		return membroAssegnato;
	}
}