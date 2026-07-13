		package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import controllers.*;
import entities.Attivita;
import entities.AttivitaDocumentazione;
import entities.AttivitaSviluppo;
import entities.Membro;
import entities.Progetto;

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
	
	private final Color BG_APP = new Color(245, 247, 250);
	private final Color BG_CARD = Color.WHITE;
	private final Color BORDER_COLOR = new Color(210, 214, 220);
	private final Color HEADER_COLOR = new Color(230, 235, 241);
	private final Color TEXT_DARK = new Color(40, 45, 50);
	
	public DashboardPanel(MainWindow mainWindow, MembroController mc, TaskController tc, ProgettoController pc) {
		this.mainWindow = mainWindow;
		this.membroController = mc;
		this.taskController = tc;
		this.progettoController = pc;
	}
	
	public void updateOnSelectedProject() {	
	    this.removeAll();
	    this.setLayout(new BorderLayout());
	    this.setBackground(BG_APP);
	    
	    Progetto currentProject = SessionController.getInstance().getCorrenteProgetto();
	    
	    if (currentProject != null) {
	    	allTasks = taskController.getTasksByProgetto(currentProject);
	    	teamMembers = membroController.getMembriByProgetto(currentProject);
	    }
	    
	    if (taskAssignments.isEmpty() && allTasks != null && teamMembers != null && !teamMembers.isEmpty()) {
	    	int memberIndex = 0;
	    	for (int i = 0; i < allTasks.size(); i++) {
	    		taskAssignments.add(teamMembers.get(memberIndex % teamMembers.size()));
	    		memberIndex++;
	    		taskComments.add(new ArrayList<String>());
	    	}
	    }
	    
	    eastPanel = new JPanel(new BorderLayout());
	    eastPanel.setBackground(BG_APP);
	    eastPanel.setPreferredSize(new Dimension(320, 0));
	    eastPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, BORDER_COLOR));
	    updateEastPanel(); 

	    JPanel westPanel = new JPanel(new BorderLayout(0, 15));
	    westPanel.setBackground(BG_APP);
	    westPanel.setPreferredSize(new Dimension(260, 0));
	    westPanel.setBorder(new EmptyBorder(20, 20, 20, 10));

	    JPanel userInfoPanel = new JPanel(new BorderLayout(15, 0));
	    userInfoPanel.setBackground(BG_CARD);
	    userInfoPanel.setBorder(new CompoundBorder(
	    		BorderFactory.createLineBorder(BORDER_COLOR, 1),
	    		new EmptyBorder(10, 15, 10, 15)
	    ));
	    
	    JLabel userIconLabel = new JLabel("UT", SwingConstants.CENTER);
	    userIconLabel.setPreferredSize(new Dimension(45, 45));
	    userIconLabel.setOpaque(true);
	    userIconLabel.setBackground(HEADER_COLOR);
	    userIconLabel.setForeground(TEXT_DARK);
	    userIconLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
	    
	    String nomeUtenteLoggato = SessionController.getInstance().isUtenteLoggato() ? 
	    		SessionController.getInstance().getUtenteLoggato().getNome() : "Ospite";
	    JLabel userNameLabel = new JLabel(nomeUtenteLoggato); 
	    userNameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
	    userNameLabel.setForeground(TEXT_DARK);
	    
	    userInfoPanel.add(userIconLabel, BorderLayout.WEST);
	    userInfoPanel.add(userNameLabel, BorderLayout.CENTER);

	    JPanel projectsContainer = new JPanel(new BorderLayout());
	    projectsContainer.setBackground(BG_APP);
	    
	    JPanel membersListPanel = new JPanel();
	    membersListPanel.setLayout(new BoxLayout(membersListPanel, BoxLayout.Y_AXIS));
	    membersListPanel.setBackground(BG_CARD);
	    
	    if (teamMembers != null) {
	        for (int i = 0; i < teamMembers.size(); i++) {
	        	Membro m = teamMembers.get(i);
	            JPanel memberPanel = new JPanel(new BorderLayout());
	            memberPanel.setBackground(BG_CARD);
	            memberPanel.setBorder(new CompoundBorder(
	                    BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
	                    new EmptyBorder(10, 10, 10, 10)
	            ));
	            memberPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
	            
	            String nomeCompleto = m.getUtente().getNome() + " " + m.getUtente().getCognome();
	            JLabel nameLabel = new JLabel(nomeCompleto);
	            nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
	            
	            JLabel statusLabel = new JLabel(m.getStatoPartecipazione());
	            statusLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
	            
	            if(m.getStatoPartecipazione().equalsIgnoreCase("Attivo")) {
	                statusLabel.setForeground(new Color(40, 167, 69));
	            } else if(m.getStatoPartecipazione().equalsIgnoreCase("Pausa")) {
	                statusLabel.setForeground(new Color(255, 193, 7));
	            } else {
	                statusLabel.setForeground(new Color(220, 53, 69));
	            }

	            memberPanel.add(nameLabel, BorderLayout.CENTER);
	            memberPanel.add(statusLabel, BorderLayout.EAST);
	            membersListPanel.add(memberPanel);
	        }
	    }
	    
	    JScrollPane membersScrollPane = new JScrollPane(membersListPanel);
	    membersScrollPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
	    
	    JLabel membersTitle = new JLabel("Membri del Progetto");
	    membersTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
	    membersTitle.setForeground(TEXT_DARK);
	    membersTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
	    
	    JPanel membersContainerWithTitle = new JPanel(new BorderLayout());
	    membersContainerWithTitle.setBackground(BG_APP);
	    membersContainerWithTitle.add(membersTitle, BorderLayout.NORTH);
	    membersContainerWithTitle.add(membersScrollPane, BorderLayout.CENTER);
	    
	    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
	    buttonsPanel.setBackground(BG_APP);
	    buttonsPanel.add(createStyledButton("Invita Membro"));
	    buttonsPanel.add(createStyledButton("Gestisci"));
	    
	    projectsContainer.add(membersContainerWithTitle, BorderLayout.CENTER);
	    projectsContainer.add(buttonsPanel, BorderLayout.SOUTH);

	    westPanel.add(userInfoPanel, BorderLayout.NORTH);
	    westPanel.add(projectsContainer, BorderLayout.CENTER);

	    JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0)); 
	    centerPanel.setBackground(BG_APP);
	    centerPanel.setBorder(new EmptyBorder(20, 10, 20, 20)); 

	    JPanel infoPlaceholder = new JPanel(new BorderLayout(0, 15));
	    infoPlaceholder.setBackground(BG_APP);
	    
	    JPanel filterPanel = new JPanel(new GridLayout(2, 4, 10, 10));
	    filterPanel.setBackground(BG_CARD);
	    filterPanel.setBorder(new CompoundBorder(
	    		BorderFactory.createLineBorder(BORDER_COLOR, 1),
	    		new EmptyBorder(15, 15, 15, 15)
	    ));
	    
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
	    
	    ActionListener filterListener = e -> applyFilters();
	    
	    statusFilter.addActionListener(filterListener);
	    typeFilter.addActionListener(filterListener);
	    dateFilter.addActionListener(filterListener);
	    memberFilter.addActionListener(filterListener);
	    
	    filterPanel.add(new JLabel("Stato:"));
	    filterPanel.add(statusFilter);
	    filterPanel.add(new JLabel("Tipo:"));
	    filterPanel.add(typeFilter);
	    filterPanel.add(new JLabel("Scadenza:"));
	    filterPanel.add(dateFilter);
	    filterPanel.add(new JLabel("Membro:"));
	    filterPanel.add(memberFilter);
	    
	    infoPlaceholder.add(filterPanel, BorderLayout.NORTH);
	    
	    tasksListPanel = new JPanel();
	    tasksListPanel.setLayout(new BoxLayout(tasksListPanel, BoxLayout.Y_AXIS));
	    tasksListPanel.setBackground(BG_APP);
	    
	    applyFilters();
	    
	    JScrollPane scrollPane = new JScrollPane(tasksListPanel);
	    scrollPane.setBorder(null);
	    scrollPane.getViewport().setBackground(BG_APP);
	    infoPlaceholder.add(scrollPane, BorderLayout.CENTER);

	    JPanel infoButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
	    infoButtonsPanel.setBackground(BG_APP);
	    
	    JButton addTaskBtn = createStyledButton("Nuova Attività");
	    
	    addTaskBtn.addActionListener(e -> {
	    	Progetto p = SessionController.getInstance().getCorrenteProgetto();
	    	AggiungiTaskDialog dialog = new AggiungiTaskDialog(mainWindow, p, teamMembers);
	    	dialog.setVisible(true);
	    	
	    	if (dialog.isConfermato()) {
	    		Attivita nuova = dialog.getNuovaAttivita();
	    		Membro assegnatario = dialog.getMembroAssegnato();
	    		
	    		boolean successo = taskController.inserisciNuovaAttivita(nuova, p, assegnatario);
	    		
	    		if (successo) {
	    			allTasks = taskController.getTasksByProgetto(p);
	    			updateOnSelectedProject();
	    		}
	    	}
	    });
	    
	    JButton generaReportButton = createStyledButton("Genera Report");
	    
	    generaReportButton.addActionListener(e -> {
	    	ReportDialog reportDialog = new ReportDialog(allTasks, taskAssignments);
	    	reportDialog.setVisible(true);
	    });
	    
	    infoButtonsPanel.add(addTaskBtn);
	    infoButtonsPanel.add(generaReportButton);
	    infoPlaceholder.add(infoButtonsPanel, BorderLayout.SOUTH);

	    JPanel kanbanPanel = new JPanel(new GridLayout(1, 3, 15, 0)); 
	    kanbanPanel.setBackground(BG_APP);
	    
	    String[] columns = {"Todo", "In_Progress", "Done"};
	    for (int i = 0; i < columns.length; i++) {
	    	String colName = columns[i];
	        JPanel columnPanel = new JPanel(new BorderLayout());
	        columnPanel.setBackground(HEADER_COLOR);
	        columnPanel.setBorder(new CompoundBorder(
	        		BorderFactory.createLineBorder(BORDER_COLOR, 1),
	        		new EmptyBorder(10, 10, 10, 10)
	        ));
	        
	        JLabel titleLabel = new JLabel(colName.replace("_", " "), SwingConstants.CENTER);
	        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
	        titleLabel.setForeground(TEXT_DARK);
	        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
	        
	        JPanel columnTasksContainer = new JPanel();
	        columnTasksContainer.setLayout(new BoxLayout(columnTasksContainer, BoxLayout.Y_AXIS));
	        columnTasksContainer.setBackground(HEADER_COLOR);
	        
	        if(allTasks != null) {
	            for(int j = 0; j < allTasks.size(); j++) {
	            	Attivita t = allTasks.get(j);
	                if(t.getStatoAvanzamento().equals(colName)) {
	                    JPanel cardPanel = new JPanel(new BorderLayout(5, 5));
	                    cardPanel.setBackground(BG_CARD);
	                    cardPanel.setBorder(new CompoundBorder(
	                    		BorderFactory.createLineBorder(BORDER_COLOR, 1),
	                            new EmptyBorder(10, 10, 10, 10)
	                    ));
	                    cardPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
	                    
	                    JLabel descLabel = new JLabel("<html><body style='width: 120px'>" + t.getDescrizione() + "</body></html>");
	                    descLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
	                    
	                    JLabel idLabel = new JLabel("#" + t.getIdAttivita());
	                    idLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
	                    idLabel.setForeground(Color.GRAY);
	                    
	                    cardPanel.add(idLabel, BorderLayout.NORTH);
	                    cardPanel.add(descLabel, BorderLayout.CENTER);
	                    
	                    cardPanel.addMouseListener(new MouseAdapter() {
	                    	public void mouseClicked(MouseEvent e) {
	                    		selectedTask = t;
	                    		updateEastPanel();
	                    	}
	                    	public void mouseEntered(MouseEvent e) {
	                    		cardPanel.setBorder(new CompoundBorder(
	                            		BorderFactory.createLineBorder(new Color(150, 150, 150), 1),
	                                    new EmptyBorder(10, 10, 10, 10)
	                            ));
	                    	}
	                    	public void mouseExited(MouseEvent e) {
	                    		cardPanel.setBorder(new CompoundBorder(
	                            		BorderFactory.createLineBorder(BORDER_COLOR, 1),
	                                    new EmptyBorder(10, 10, 10, 10)
	                            ));
	                    	}
						});
	                    
	                    columnTasksContainer.add(cardPanel);
	                    columnTasksContainer.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
	                }
	            }
	        }
	        
	        JScrollPane kanbanScroll = new JScrollPane(columnTasksContainer);
	        kanbanScroll.setBorder(null);
	        kanbanScroll.getViewport().setBackground(HEADER_COLOR);
	        kanbanScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        
	        columnPanel.add(titleLabel, BorderLayout.NORTH);
	        columnPanel.add(kanbanScroll, BorderLayout.CENTER);
	        
	        kanbanPanel.add(columnPanel);
	    }

	    centerPanel.add(infoPlaceholder);
	    centerPanel.add(kanbanPanel);

	    this.add(westPanel, BorderLayout.WEST);
	    this.add(eastPanel, BorderLayout.EAST);
	    this.add(centerPanel, BorderLayout.CENTER);
	    
	    this.revalidate();
	    this.repaint();
	}
	
	private JButton createStyledButton(String text) {
		JButton btn = new JButton(text);
		btn.setBackground(BG_CARD);
		btn.setForeground(TEXT_DARK);
		btn.setFont(new Font("SansSerif", Font.BOLD, 12));
		btn.setFocusPainted(false);
		btn.setBorder(new CompoundBorder(
				BorderFactory.createLineBorder(BORDER_COLOR, 1),
				new EmptyBorder(8, 15, 8, 15)
		));
		return btn;
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
                JPanel singleTaskPanel = new JPanel(new BorderLayout(10, 10));
                singleTaskPanel.setBackground(BG_CARD);
                singleTaskPanel.setBorder(new CompoundBorder(
                        BorderFactory.createLineBorder(BORDER_COLOR, 1),
                        new EmptyBorder(12, 15, 12, 15)
                ));
                singleTaskPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); 
                
                String statusColor = t.getStatoAvanzamento().equals("Done") ? "green" : (t.getStatoAvanzamento().equals("Todo") ? "gray" : "orange");
                
                JLabel descLabel = new JLabel("<html><b>" + t.getDescrizione() + "</b> <span style='color:"+statusColor+"'>[" + t.getStatoAvanzamento().replace("_", " ") + "]</span></html>");
                descLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
                
                JLabel dateLabel = new JLabel(t.getScadenza().toString());
                dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
                dateLabel.setForeground(Color.GRAY);
                
                singleTaskPanel.add(descLabel, BorderLayout.CENTER);
                singleTaskPanel.add(dateLabel, BorderLayout.EAST);
                
                singleTaskPanel.addMouseListener(new MouseAdapter() {
                	public void mouseClicked(MouseEvent e) {
                		selectedTask = t;
                		updateEastPanel();
                	}
                	public void mouseEntered(MouseEvent e) {
                		singleTaskPanel.setBackground(new Color(248, 250, 252));
                	}
                	public void mouseExited(MouseEvent e) {
                		singleTaskPanel.setBackground(BG_CARD);
                	}
				});
                
                container.add(singleTaskPanel);
                container.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
            }
        }
	}
	
	private void updateEastPanel() {
		if (eastPanel == null) return;
		
		eastPanel.removeAll();
		eastPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		if (selectedTask == null) {
			JLabel noSelectionLabel = new JLabel("<html><center><h2 style='color:#A0AAB5;'>Nessuna Selezione</h2><p style='color:#A0AAB5;'>Seleziona un'attività dalla lista<br>o dalla board per i dettagli.</p></center></html>", SwingConstants.CENTER);
			eastPanel.add(noSelectionLabel, BorderLayout.CENTER);
			eastPanel.revalidate();
			eastPanel.repaint();
			return;
		}
		
		JPanel detailsContainer = new JPanel();
		detailsContainer.setLayout(new BoxLayout(detailsContainer, BoxLayout.Y_AXIS));
		detailsContainer.setBackground(BG_APP);
		
		JLabel taskTitle = new JLabel("<html><p style='width: 240px; font-size: 14px; margin-bottom: 15px;'><b>" + selectedTask.getDescrizione() + "</b></p></html>");
		detailsContainer.add(taskTitle);
		
		detailsContainer.add(createDetailRow("ID Task", String.valueOf(selectedTask.getIdAttivita())));
		detailsContainer.add(createDetailRow("Creazione", selectedTask.getDataCreazione().toString()));
		detailsContainer.add(createDetailRow("Scadenza", selectedTask.getScadenza().toString()));
		
		int indiceSelezionata = allTasks.indexOf(selectedTask);
		Membro assigned = taskAssignments.get(indiceSelezionata);
		
		String mName = assigned != null ? assigned.getUtente().getNome() + " " + assigned.getUtente().getCognome() : "Non Assegnato";
		detailsContainer.add(createDetailRow("Assegnato a", mName));
		
		detailsContainer.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
		
		if (selectedTask instanceof AttivitaSviluppo) {
			AttivitaSviluppo as = (AttivitaSviluppo) selectedTask;
			detailsContainer.add(createDetailRow("Tipologia", "Sviluppo (" + as.getTipologiaSviluppo() + ")"));
			detailsContainer.add(createDetailRow("Linguaggio", as.getLinguaggioProgrammazione()));
			detailsContainer.add(createDetailRow("Branch Git", as.getNomeBranch()));
		} else if (selectedTask instanceof AttivitaDocumentazione) {
			AttivitaDocumentazione ad = (AttivitaDocumentazione) selectedTask;
			detailsContainer.add(createDetailRow("Tipologia", "Documentazione"));
			detailsContainer.add(createDetailRow("Titolo", ad.getTitoloDocu()));
			detailsContainer.add(createDetailRow("Formato", ad.getFormato()));
			detailsContainer.add(createDetailRow("Sezione", ad.getSezione()));
			detailsContainer.add(createDetailRow("Link", ad.getLinkRisorsa()));
		}
		
		detailsContainer.add(javax.swing.Box.createRigidArea(new Dimension(0, 15)));
		
		JPanel statusUpdatePanel = new JPanel(new BorderLayout(10, 0));
		statusUpdatePanel.setBackground(BG_APP);
		JLabel statusLabel = new JLabel("Stato Corrente:");
		statusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JComboBox<String> editStatusCombo = new JComboBox<>(new String[]{"Todo", "In_Progress", "Done"});
		editStatusCombo.setSelectedItem(selectedTask.getStatoAvanzamento());
		editStatusCombo.setBackground(BG_CARD);
		
		editStatusCombo.addActionListener(e -> {
			String selectedStatus = (String) editStatusCombo.getSelectedItem();
			if (!selectedStatus.equals(selectedTask.getStatoAvanzamento())) {
				Progetto p = SessionController.getInstance().getCorrenteProgetto();
				taskController.updateTaskStatus(p, selectedTask, selectedStatus);
				
				allTasks = taskController.getTasksByProgetto(p);
				for(Attivita t : allTasks) {
					if (t.getIdAttivita() == selectedTask.getIdAttivita()) {
						selectedTask = t;
						break;
					}
				}
				
				updateOnSelectedProject(); 
			}
		});
		
		statusUpdatePanel.add(statusLabel, BorderLayout.WEST);
		statusUpdatePanel.add(editStatusCombo, BorderLayout.CENTER);
		detailsContainer.add(statusUpdatePanel);
		
		JPanel commentsPanel = new JPanel(new BorderLayout(0, 10));
		commentsPanel.setBackground(BG_APP);
		commentsPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		JLabel commentsTitle = new JLabel("Commenti");
		commentsTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
		commentsTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR));
		commentsPanel.add(commentsTitle, BorderLayout.NORTH);
		
		JPanel commentsListPanel = new JPanel();
		commentsListPanel.setLayout(new BoxLayout(commentsListPanel, BoxLayout.Y_AXIS));
		commentsListPanel.setBackground(BG_APP);
		
		ArrayList<String> commentiTaskCorrente = taskComments.get(indiceSelezionata);
		for (int i = 0; i < commentiTaskCorrente.size(); i++) {
			String comment = commentiTaskCorrente.get(i);
			JPanel commentCard = new JPanel(new BorderLayout());
			commentCard.setBackground(HEADER_COLOR);
			commentCard.setBorder(new CompoundBorder(
					BorderFactory.createLineBorder(BORDER_COLOR, 1),
					new EmptyBorder(8, 10, 8, 10)
			));
			JLabel cLabel = new JLabel("<html><span style='font-size:9px; color:gray;'>User</span><br>" + comment + "</html>");
			cLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
			commentCard.add(cLabel, BorderLayout.CENTER);
			commentsListPanel.add(commentCard);
			commentsListPanel.add(javax.swing.Box.createRigidArea(new Dimension(0, 8)));
		}
		
		JScrollPane commentsScroll = new JScrollPane(commentsListPanel);
		commentsScroll.setBorder(null);
		commentsScroll.getViewport().setBackground(BG_APP);
		commentsPanel.add(commentsScroll, BorderLayout.CENTER);
		
		JPanel addCommentPanel = new JPanel(new BorderLayout(10, 0));
		addCommentPanel.setBackground(BG_APP);
		JTextField commentInputField = new JTextField();
		commentInputField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDER_COLOR, 1),
				new EmptyBorder(5, 5, 5, 5)
		));
		
		JButton sendCommentButton = createStyledButton("Invia");
		
		sendCommentButton.addActionListener(e -> {
			String commentText = commentInputField.getText().trim();
			if (!commentText.isEmpty()) {
				taskComments.get(indiceSelezionata).add(commentText);
				commentInputField.setText("");
				updateEastPanel(); 
			}
		});
		
		addCommentPanel.add(commentInputField, BorderLayout.CENTER);
		addCommentPanel.add(sendCommentButton, BorderLayout.EAST);
		commentsPanel.add(addCommentPanel, BorderLayout.SOUTH);
		
		JPanel upperLayoutFix = new JPanel(new BorderLayout());
		upperLayoutFix.setBackground(BG_APP);
		upperLayoutFix.add(detailsContainer, BorderLayout.NORTH);
		
		eastPanel.add(upperLayoutFix, BorderLayout.NORTH);
		eastPanel.add(commentsPanel, BorderLayout.CENTER);
		
		eastPanel.revalidate();
		eastPanel.repaint();
	}
	
	private JPanel createDetailRow(String label, String value) {
		JPanel row = new JPanel(new BorderLayout());
		row.setBackground(BG_APP);
		row.setBorder(new EmptyBorder(3, 0, 3, 0));
		
		JLabel l = new JLabel(label + ":");
		l.setFont(new Font("SansSerif", Font.BOLD, 12));
		l.setForeground(TEXT_DARK);
		
		JLabel v = new JLabel(value);
		v.setFont(new Font("SansSerif", Font.PLAIN, 12));
		v.setForeground(Color.DARK_GRAY);
		v.setHorizontalAlignment(SwingConstants.RIGHT);
		
		row.add(l, BorderLayout.WEST);
		row.add(v, BorderLayout.CENTER);
		return row;
	}
}
