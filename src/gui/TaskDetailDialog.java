package gui;

import java.awt.*;
import javax.swing.*;

import controllers.DashboardController;
import controllers.SessionController;
import entities.Attivita;
import entities.Membro;
import entities.Progetto;

public class TaskDetailDialog extends JDialog {

    private DashboardController dashboardController;
    private DashboardPanel dashboardPanel;
    private Attivita task;

    private JComboBox<String> statoCombo;
    private JComboBox<String> assegnatarioCombo;

    public TaskDetailDialog(MainWindow parent, Attivita task, DashboardPanel dp, DashboardController dc) {
        super(parent, "Dettaglio Attività: " + task.getIdAttivita(), true);
        this.task = task;
        this.dashboardPanel = dp;
        this.dashboardController = dc;

        this.setSize(450, 400);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel titleLabel = new JLabel("Dettagli Task");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea descArea = new JTextArea(task.getDescrizione());
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descArea.setBackground(new Color(245, 245, 245));
        descArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel scadenzaLabel = new JLabel("Scadenza: " + task.getScadenza().toString());
        scadenzaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scadenzaLabel.setForeground(Color.GRAY);

     
        String assegnatariCorrenti = dc.getNomiAssegnatariTask(task);
        JLabel assegnatariLabel = new JLabel("Assegnatari: " + assegnatariCorrenti);
        assegnatariLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        assegnatariLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        
        JPanel statoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statoPanel.setBackground(Color.WHITE);
        statoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statoPanel.add(new JLabel("Stato attuale: "));
        
        
        statoCombo = new JComboBox<>(new String[]{"Todo", "In_progress", "Done"});
        statoCombo.setSelectedItem(task.getStatoAvanzamento());
        statoPanel.add(statoCombo);

        
        JPanel assegnaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        assegnaPanel.setBackground(Color.WHITE);
        assegnaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        assegnaPanel.add(new JLabel("Assegna a: "));
        
        assegnatarioCombo = new JComboBox<>();
        assegnatarioCombo.addItem("Nessuna nuova assegnazione");
        for (Membro m : dc.getListaMembri()) {
            assegnatarioCombo.addItem(m.getUtente().getNome() + " " + m.getUtente().getCognome());
        }
        assegnaPanel.add(assegnatarioCombo);

        
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton annullaBtn = new JButton("Annulla");
        annullaBtn.addActionListener(e -> this.dispose());
        
        JButton salvaBtn = new JButton("Salva Modifiche");
        salvaBtn.addActionListener(e -> salvaModifiche());
        
        buttonsPanel.add(annullaBtn);
        buttonsPanel.add(salvaBtn);

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(descArea);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(scadenzaLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        mainPanel.add(assegnatariLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(statoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(assegnaPanel);
        mainPanel.add(Box.createVerticalGlue()); 
        mainPanel.add(buttonsPanel);

        this.add(mainPanel);
        this.setVisible(true);
    }

    private void salvaModifiche() {
        Progetto progettoCorrente = SessionController.getInstance().getCorrenteProgetto();

        String nuovoStato = (String) statoCombo.getSelectedItem();
        if (!nuovoStato.equals(task.getStatoAvanzamento())) {
            dashboardController.getTaskController().updateTaskStatus(progettoCorrente, task, nuovoStato);
        }

        int memberIndex = assegnatarioCombo.getSelectedIndex();
        if (memberIndex > 0) { 
            Membro nuovoAssegnatario = dashboardController.getListaMembri().get(memberIndex - 1);
            dashboardController.getTaskController().assignTaskTo(nuovoAssegnatario, progettoCorrente, task);
        }

        dashboardPanel.update();
        this.dispose();
    }
}