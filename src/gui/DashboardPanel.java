package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controllers.DashboardController;
import entities.Attivita;
import entities.AttivitaDocumentazione;
import entities.AttivitaSviluppo;
import entities.Membro;
import exceptions.LoadDataException;

public class DashboardPanel extends JPanel implements ActionListener{

	private MainWindow mainWindow;
	private DashboardController dashboardController;
	
	private JPanel headerPanel;
	private JPanel bodyPanel;
	private JButton addTaskBtn;
	private JButton reportBtn;
	private JComboBox<String> tipoFilterCombo;
	private JComboBox<String> scadenzaFilterCombo;
	private JComboBox<String> membroFilterCombo;

	public DashboardPanel(MainWindow mainWindow, DashboardController dashboardController) {
		this.mainWindow = mainWindow;
		this.dashboardController = dashboardController;
		this.setLayout(new BorderLayout());
	}
	
	public void update() {	
		try {
			this.removeAll();
			this.mainWindow.setSize(new Dimension(1100, 700));
			this.mainWindow.setResizable(false);
			
			dashboardController.loadData();
			
			loadNordPanel();
			loadCenterPanel();
			
			this.add(headerPanel, BorderLayout.NORTH);
			this.add(bodyPanel, BorderLayout.CENTER);
			
			this.revalidate();
			this.repaint();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void updateBody() {
		
		if (bodyPanel != null) {
			this.remove(bodyPanel);
		}
				
		
		loadCenterPanel();
		this.add(bodyPanel, BorderLayout.CENTER); 
			
		this.revalidate();
		this.repaint();
	}
	
	private void loadNordPanel() {
		
		headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		headerPanel.setBackground(new Color(244, 245, 247)); 
		
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
		
		tipoFilterCombo = new JComboBox<>(new String[]{"Tutte", "Sviluppo", "Documentazione"});
		tipoFilterCombo.setBackground(Color.WHITE);
		
		scadenzaFilterCombo = new JComboBox<>(new String[]{"Tutte le date", "Scadute", "In scadenza"});
		scadenzaFilterCombo.setBackground(Color.WHITE);
		
		membroFilterCombo = new JComboBox<>();
		membroFilterCombo.setBackground(Color.WHITE);
		membroFilterCombo.addItem("Tutti i membri");
		membroFilterCombo.addItem("Nessuno");
		for(Membro m : dashboardController.getListaMembri()) {
			membroFilterCombo.addItem(m.getUtente().getNome() + " " + m.getUtente().getCognome());
		}

		ActionListener filterListener = e -> updateBody();
		tipoFilterCombo.addActionListener(filterListener);
		scadenzaFilterCombo.addActionListener(filterListener);
		membroFilterCombo.addActionListener(filterListener);
		
		headerPanel.add(new JLabel("Tipo:"));
		headerPanel.add(tipoFilterCombo);
		headerPanel.add(new JLabel("Scadenza:"));
		headerPanel.add(scadenzaFilterCombo);
		headerPanel.add(new JLabel("Assegnatario:"));
		headerPanel.add(membroFilterCombo);
		
		addTaskBtn = new JButton("+ New Task");
		addTaskBtn.addActionListener(this);
		reportBtn = new JButton("Report");
		reportBtn.addActionListener(this);
		headerPanel.add(addTaskBtn);
		headerPanel.add(reportBtn);
	}

	private void loadCenterPanel() {
		bodyPanel = new JPanel(new GridLayout(1, 3, 20, 0));
		
		bodyPanel.setBackground(new Color(244, 245, 247));
		
		bodyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		String tipo = (String) tipoFilterCombo.getSelectedItem();
		String scadenza = (String) scadenzaFilterCombo.getSelectedItem();
		String membro = (String) membroFilterCombo.getSelectedItem();

		bodyPanel.add(new CardTaskPanel(dashboardController.getListaTasksFiltered("Todo", tipo, scadenza, membro), "To Do", this, dashboardController));
		bodyPanel.add(new CardTaskPanel(dashboardController.getListaTasksFiltered("In_progress", tipo, scadenza, membro), "In Progress", this, dashboardController));
		bodyPanel.add(new CardTaskPanel(dashboardController.getListaTasksFiltered("Done", tipo, scadenza, membro), "Done", this, dashboardController));	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addTaskBtn) {
			
			new AddTaskFrame(mainWindow, dashboardController, this);
			
		}
		
		if(e.getSource() == reportBtn) {
			
			new ReportDialog(mainWindow, dashboardController);
			
		}
		
	}
}