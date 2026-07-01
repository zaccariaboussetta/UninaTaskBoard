package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.*;
import entities.Attivita;
import entities.AttivitaDocumentazione;
import entities.AttivitaSviluppo;
import entities.Membro;

public class DashboardPanel extends JPanel {

	private MainWindow mainWindow;
	private MembroController membroController;
	private TaskController taskController;
	private ProgettoController progettoController;
	
	private ArrayList<Attivita> allTasks;
	private ArrayList<Membro> teamMembers;
	private Attivita selectedTask = null;
	
	private JPanel eastPanel;
	private JPanel tasksListPanel;
	
	private JComboBox<String> statusFilter;
	private JComboBox<String> typeFilter;
	private JComboBox<String> dateFilter;
	private JComboBox<String> memberFilter;
	
	private ArrayList<Membro> taskAssignments = new ArrayList<>();
	private ArrayList<ArrayList<String>> taskComments = new ArrayList<>();
	
	public DashboardPanel(MainWindow mainWindow, MembroController mc, TaskController tc, ProgettoController pc) {
		this.mainWindow = mainWindow;
		this.membroController = mc;
		this.taskController = tc;
		this.progettoController = pc;
		
		mainWindow.setResizable(true);
	}
	
	public void updateOnSelectedProject() {	
	    
	    this.removeAll();
	    this.setLayout(new BorderLayout());
	    
	    if (allTasks == null && taskController != null) {
	    	allTasks = taskController.getTasksByProgetto(null);
	    }
	    if (teamMembers == null && membroController != null) {
	    	teamMembers = membroController.getMembriByProgetto(null);
	    }
	    
	    if (taskAssignments.isEmpty() && allTasks != null && teamMembers != null && !teamMembers.isEmpty()) {
	    	int memberIndex = 0;
	    	for (int i = 0; i < allTasks.size(); i++) {
	    		taskAssignments.add(teamMembers.get(memberIndex % teamMembers.size()));
	    		memberIndex++;
	    		
	    		taskComments.add(new ArrayList<String>());
	    	}
	    }
	    
	    JPanel northPanel = new JPanel();
	    northPanel.setBackground(Color.BLUE);
	    northPanel.setPreferredSize(new Dimension(0, 30));
	    
	    JPanel southPanel = new JPanel();
	    southPanel.setBackground(Color.BLUE);
	    southPanel.setPreferredSize(new Dimension(0, 30));

	    eastPanel = new JPanel();
	    eastPanel.setBackground(Color.WHITE);
	    eastPanel.setPreferredSize(new Dimension(300, 0));
	    eastPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));
	    updateEastPanel(); 

	    JPanel westPanel = new JPanel(new BorderLayout(0, 20));
	    westPanel.setPreferredSize(new Dimension(250, 0));
	    westPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    JPanel userInfoPanel = new JPanel(new BorderLayout(10, 0));
	    JLabel userIconLabel = new JLabel("[Icona]");
	    userIconLabel.setPreferredSize(new Dimension(50, 50));
	    userIconLabel.setOpaque(true);
	    userIconLabel.setBackground(Color.WHITE);
	    
	    JLabel userNameLabel = new JLabel("Nome Utente"); 
	    userInfoPanel.add(userIconLabel, BorderLayout.WEST);
	    userInfoPanel.add(userNameLabel, BorderLayout.CENTER);

	    JPanel projectsContainer = new JPanel(new BorderLayout());
	    JPanel membersListPanel = new JPanel();
	    membersListPanel.setLayout(new BoxLayout(membersListPanel, BoxLayout.Y_AXIS));
	    membersListPanel.setBackground(Color.WHITE);
	    
	    if (teamMembers != null) {
	        for (int i = 0; i < teamMembers.size(); i++) {
	        	Membro m = teamMembers.get(i);
	            JPanel memberPanel = new JPanel(new BorderLayout());
	            memberPanel.setBorder(BorderFactory.createCompoundBorder(
	                    BorderFactory.createEmptyBorder(5, 5, 5, 5),
	                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)
	            ));
	            memberPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
	            
	            String nomeCompleto = m.getUtente().getNome() + " " + m.getUtente().getCognome();
	            JLabel nameLabel = new JLabel(" " + nomeCompleto);
	            JLabel statusLabel = new JLabel("[" + m.getStatoPartecipazione() + "] ");
	            
	            if(m.getStatoPartecipazione().equalsIgnoreCase("Attivo")) {
	                statusLabel.setForeground(new Color(0, 150, 0));
	            } else if(m.getStatoPartecipazione().equalsIgnoreCase("In pausa")) {
	                statusLabel.setForeground(Color.ORANGE);
	            } else {
	                statusLabel.setForeground(Color.RED);
	            }

	            memberPanel.add(nameLabel, BorderLayout.CENTER);
	            memberPanel.add(statusLabel, BorderLayout.EAST);
	            membersListPanel.add(memberPanel);
	        }
	    }
	    
	    JScrollPane membersScrollPane = new JScrollPane(membersListPanel);
	    JLabel membersTitle = new JLabel("Membri del Progetto", SwingConstants.CENTER);
	    membersTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
	    
	    JPanel membersContainerWithTitle = new JPanel(new BorderLayout());
	    membersContainerWithTitle.add(membersTitle, BorderLayout.NORTH);
	    membersContainerWithTitle.add(membersScrollPane, BorderLayout.CENTER);
	    
	    JPanel buttonsPanel = new JPanel();
	    buttonsPanel.add(new JButton("Crea"));
	    buttonsPanel.add(new JButton("Cambia"));
	    
	    projectsContainer.add(membersContainerWithTitle, BorderLayout.CENTER);
	    projectsContainer.add(buttonsPanel, BorderLayout.SOUTH);

	    westPanel.add(userInfoPanel, BorderLayout.NORTH);
	    westPanel.add(projectsContainer, BorderLayout.CENTER);

	    JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0)); 
	    centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

	    JPanel infoPlaceholder = new JPanel(new BorderLayout());
	    infoPlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    JPanel filterPanel = new JPanel(new GridLayout(2, 4, 5, 5));
	    filterPanel.setBorder(BorderFactory.createTitledBorder("Filtra Attività"));
	    
	    statusFilter = new JComboBox<>(new String[]{"Tutti", "Todo", "In_Progress", "Done"});
	    typeFilter = new JComboBox<>(new String[]{"Tutte", "Sviluppo", "Documentazione"});
	    dateFilter = new JComboBox<>(new String[]{"Tutte", "Scadute", "In Scadenza (7gg)"});
	    
	    ArrayList<String> nomiMembri = new ArrayList<>();
	    nomiMembri.add("Tutti");
	    if (teamMembers != null) {
	    	for(int i = 0; i < teamMembers.size(); i++) {
	    		Membro m = teamMembers.get(i);
	    		nomiMembri.add(m.getUtente().getNome() + " " + m.getUtente().getCognome());
	    	}
	    }
	    memberFilter = new JComboBox<>(nomiMembri.toArray(new String[0]));
	    
	    ActionListener filterListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applyFilters();
			}
		};
	    
	    statusFilter.addActionListener(filterListener);
	    typeFilter.addActionListener(filterListener);
	    dateFilter.addActionListener(filterListener);
	    memberFilter.addActionListener(filterListener);
	    
	    filterPanel.add(new JLabel(" Stato:", SwingConstants.RIGHT));
	    filterPanel.add(statusFilter);
	    filterPanel.add(new JLabel(" Tipo:", SwingConstants.RIGHT));
	    filterPanel.add(typeFilter);
	    filterPanel.add(new JLabel(" Scadenza:", SwingConstants.RIGHT));
	    filterPanel.add(dateFilter);
	    filterPanel.add(new JLabel(" Membro:", SwingConstants.RIGHT));
	    filterPanel.add(memberFilter);
	    
	    infoPlaceholder.add(filterPanel, BorderLayout.NORTH);
	    
	    tasksListPanel = new JPanel();
	    tasksListPanel.setLayout(new BoxLayout(tasksListPanel, BoxLayout.Y_AXIS));
	    tasksListPanel.setBackground(Color.WHITE);
	    
	    applyFilters();
	    
	    JScrollPane scrollPane = new JScrollPane(tasksListPanel);
	    infoPlaceholder.add(scrollPane, BorderLayout.CENTER);

	    JPanel infoButtonsPanel = new JPanel();
	    infoButtonsPanel.add(new JButton("Aggiungi task"));
	    
	    JButton generaReportButton = new JButton("Genera Report");
	    generaReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportDialog reportDialog = new ReportDialog(allTasks, taskAssignments);
				reportDialog.setVisible(true);
			}
		});
	    infoButtonsPanel.add(generaReportButton);
	    
	    infoPlaceholder.add(infoButtonsPanel, BorderLayout.SOUTH);

	    JPanel kanbanPanel = new JPanel(new GridLayout(1, 3, 0, 0)); 
	    
	    String[] columns = {"Todo", "In_Progress", "Done"};
	    for (int i = 0; i < columns.length; i++) {
	    	String colName = columns[i];
	        JPanel columnPanel = new JPanel(new BorderLayout());
	        columnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
	        
	        JLabel titleLabel = new JLabel(colName, SwingConstants.CENTER);
	        
	        JPanel columnTasksContainer = new JPanel();
	        columnTasksContainer.setLayout(new BoxLayout(columnTasksContainer, BoxLayout.Y_AXIS));
	        columnTasksContainer.setBackground(Color.LIGHT_GRAY);
	        
	        if(allTasks != null) {
	            for(int j = 0; j < allTasks.size(); j++) {
	            	Attivita t = allTasks.get(j);
	                if(t.getStatoAvanzamento().equals(colName)) {
	                    JPanel cardPanel = new JPanel(new BorderLayout());
	                    cardPanel.setBorder(BorderFactory.createCompoundBorder(
	                            BorderFactory.createEmptyBorder(5, 5, 5, 5),
	                            BorderFactory.createLineBorder(Color.DARK_GRAY, 1)
	                    ));
	                    cardPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
	                    
	                    JLabel descLabel = new JLabel(" " + t.getDescrizione());
	                    cardPanel.add(descLabel, BorderLayout.CENTER);
	                    
	                    cardPanel.addMouseListener(new MouseAdapter() {
	                    	public void mouseClicked(MouseEvent e) {
	                    		selectedTask = t;
	                    		updateEastPanel();
	                    	}
						});
	                    
	                    columnTasksContainer.add(cardPanel);
	                }
	            }
	        }
	        
	        JScrollPane kanbanScroll = new JScrollPane(columnTasksContainer);
	        
	        columnPanel.add(titleLabel, BorderLayout.NORTH);
	        columnPanel.add(kanbanScroll, BorderLayout.CENTER);
	        
	        kanbanPanel.add(columnPanel);
	    }

	    centerPanel.add(infoPlaceholder);
	    centerPanel.add(kanbanPanel);

	    this.add(westPanel, BorderLayout.WEST);
	    this.add(eastPanel, BorderLayout.EAST);
	    this.add(northPanel, BorderLayout.NORTH);
	    this.add(southPanel, BorderLayout.SOUTH);
	    this.add(centerPanel, BorderLayout.CENTER);
	    
	    this.revalidate();
	    this.repaint();
	}
	
	private void applyFilters() {
		if (allTasks == null || tasksListPanel == null) return;
		
		ArrayList<Attivita> filteredTasks = new ArrayList<>();
		String selStatus = (String) statusFilter.getSelectedItem();
		String selType = (String) typeFilter.getSelectedItem();
		String selDate = (String) dateFilter.getSelectedItem();
		String selMember = (String) memberFilter.getSelectedItem();
		
		for (int i = 0; i < allTasks.size(); i++) {
			Attivita t = allTasks.get(i);
			
			if (!selStatus.equals("Tutti") && !t.getStatoAvanzamento().equals(selStatus)) continue;
			
			if (!selType.equals("Tutte")) {
				if (selType.equals("Sviluppo") && !(t instanceof AttivitaSviluppo)) continue;
				if (selType.equals("Documentazione") && !(t instanceof AttivitaDocumentazione)) continue;
			}
			
			if (!selMember.equals("Tutti")) {
				int indiceTask = allTasks.indexOf(t);
				Membro assigned = taskAssignments.get(indiceTask);
				
				if (assigned == null) continue;
				String nomeMembro = assigned.getUtente().getNome() + " " + assigned.getUtente().getCognome();
				if (!nomeMembro.equals(selMember)) continue;
			}
			
			if (!selDate.equals("Tutte")) {
				LocalDate now = LocalDate.now();
				if (selDate.equals("Scadute") && !t.getScadenza().isBefore(now)) continue;
				if (selDate.equals("In Scadenza (7gg)") && (t.getScadenza().isBefore(now) || t.getScadenza().isAfter(now.plusDays(7)))) continue;
			}
			
			filteredTasks.add(t);
		}
		
		tasksListPanel.removeAll();
		loadTasksIntoContainer(tasksListPanel, filteredTasks);
		tasksListPanel.revalidate();
		tasksListPanel.repaint();
	}
	
	private void loadTasksIntoContainer(JPanel container, ArrayList<Attivita> tasks) {
        if(tasks != null) {
            for (int i = 0; i < tasks.size(); i++) {
            	Attivita t = tasks.get(i);
                JPanel singleTaskPanel = new JPanel(new BorderLayout());
                singleTaskPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 5, 5),
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)
                ));
                singleTaskPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); 
                
                JLabel descLabel = new JLabel(" " + t.getDescrizione() + " [" + t.getStatoAvanzamento() + "]");
                JLabel dateLabel = new JLabel("Scad: " + t.getScadenza() + " ");
                
                singleTaskPanel.add(descLabel, BorderLayout.CENTER);
                singleTaskPanel.add(dateLabel, BorderLayout.EAST);
                
                singleTaskPanel.addMouseListener(new MouseAdapter() {
                	public void mouseClicked(MouseEvent e) {
                		selectedTask = t;
                		updateEastPanel();
                	}
				});
                
                container.add(singleTaskPanel);
            }
        }
	}
	
	private void updateEastPanel() {
		if (eastPanel == null) return;
		
		eastPanel.removeAll();
		eastPanel.setLayout(new BorderLayout());
		eastPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
		
		if (selectedTask == null) {
			JLabel noSelectionLabel = new JLabel("<html><center>Seleziona una task dalla lista<br>o dal Kanban per vedere i dettagli</center></html>", SwingConstants.CENTER);
			noSelectionLabel.setForeground(Color.GRAY);
			eastPanel.add(noSelectionLabel, BorderLayout.CENTER);
			eastPanel.revalidate();
			eastPanel.repaint();
			return;
		}
		
		JPanel detailsContainer = new JPanel();
		detailsContainer.setLayout(new BoxLayout(detailsContainer, BoxLayout.Y_AXIS));
		detailsContainer.setBorder(BorderFactory.createTitledBorder("Dettagli Attività"));
		
		detailsContainer.add(new JLabel("ID Task: " + selectedTask.getIdAttivita()));
		detailsContainer.add(new JLabel("Descrizione: " + selectedTask.getDescrizione()));
		detailsContainer.add(new JLabel("Data Creazione: " + selectedTask.getDataCreazione()));
		detailsContainer.add(new JLabel("Scadenza: " + selectedTask.getScadenza()));
		
		int indiceSelezionata = allTasks.indexOf(selectedTask);
		Membro assigned = taskAssignments.get(indiceSelezionata);
		
		String mName = assigned != null ? assigned.getUtente().getNome() + " " + assigned.getUtente().getCognome() : "Non Assegnato";
		detailsContainer.add(new JLabel("Assegnato a: " + mName));
		
		if (selectedTask instanceof AttivitaSviluppo) {
			AttivitaSviluppo as = (AttivitaSviluppo) selectedTask;
			detailsContainer.add(new JLabel("Tipologia: Sviluppo (" + as.getTipologiaSviluppo() + ")"));
			detailsContainer.add(new JLabel("Linguaggio: " + as.getLinguaggioProgrammazione()));
			detailsContainer.add(new JLabel("Branch Git: " + as.getNomeBranch()));
		} else if (selectedTask instanceof AttivitaDocumentazione) {
			AttivitaDocumentazione ad = (AttivitaDocumentazione) selectedTask;
			detailsContainer.add(new JLabel("Tipologia: Documentazione"));
			detailsContainer.add(new JLabel("Titolo Documento: " + ad.getTitoloDocu()));
			detailsContainer.add(new JLabel("Formato: " + ad.getFormato()));
			detailsContainer.add(new JLabel("Sezione: " + ad.getSezione()));
			detailsContainer.add(new JLabel("Link Risorsa: " + ad.getLinkRisorsa()));
		}
		
		JPanel statusUpdatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		statusUpdatePanel.add(new JLabel("Stato: "));
		JComboBox<String> editStatusCombo = new JComboBox<>(new String[]{"Todo", "In_Progress", "Done"});
		editStatusCombo.setSelectedItem(selectedTask.getStatoAvanzamento());
		
		editStatusCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedStatus = (String) editStatusCombo.getSelectedItem();
				if (!selectedStatus.equals(selectedTask.getStatoAvanzamento())) {
					selectedTask.setStatoAvanzamento(selectedStatus);
					updateOnSelectedProject(); 
				}
			}
		});
		
		statusUpdatePanel.add(editStatusCombo);
		detailsContainer.add(statusUpdatePanel);
		
		JPanel commentsPanel = new JPanel(new BorderLayout());
		commentsPanel.setBorder(BorderFactory.createTitledBorder("Commenti"));
		
		JPanel commentsListPanel = new JPanel();
		commentsListPanel.setLayout(new BoxLayout(commentsListPanel, BoxLayout.Y_AXIS));
		commentsListPanel.setBackground(Color.WHITE);
		
		ArrayList<String> commentiTaskCorrente = taskComments.get(indiceSelezionata);
		for (int i = 0; i < commentiTaskCorrente.size(); i++) {
			String comment = commentiTaskCorrente.get(i);
			JLabel cLabel = new JLabel("<html><b>User:</b> " + comment + "</html>");
			cLabel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
			commentsListPanel.add(cLabel);
		}
		
		JScrollPane commentsScroll = new JScrollPane(commentsListPanel);
		commentsPanel.add(commentsScroll, BorderLayout.CENTER);
		
		JPanel addCommentPanel = new JPanel(new BorderLayout());
		JTextField commentInputField = new JTextField();
		JButton sendCommentButton = new JButton("Invia");
		
		sendCommentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commentText = commentInputField.getText().trim();
				if (!commentText.isEmpty()) {
					taskComments.get(indiceSelezionata).add(commentText);
					commentInputField.setText("");
					updateEastPanel(); 
				}
			}
		});
		
		addCommentPanel.add(commentInputField, BorderLayout.CENTER);
		addCommentPanel.add(sendCommentButton, BorderLayout.EAST);
		commentsPanel.add(addCommentPanel, BorderLayout.SOUTH);
		
		JPanel upperLayoutFix = new JPanel(new BorderLayout());
		upperLayoutFix.add(detailsContainer, BorderLayout.NORTH);
		
		eastPanel.add(upperLayoutFix, BorderLayout.NORTH);
		eastPanel.add(commentsPanel, BorderLayout.CENTER);
		
		eastPanel.revalidate();
		eastPanel.repaint();
	}
}
