package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import controllers.DashboardController;
import entities.Attivita;

public class CardTask extends JPanel implements ActionListener{

	private Attivita task;
	private DashboardPanel dp;
	private DashboardController dc;
	private JButton dettagliBtn;
	
	public CardTask(Attivita task, DashboardPanel dp, DashboardController dc) {
		
		this.task = task;
		this.dp = dp;
		this.dc = dc;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.WHITE);
		
		
		Border linea = BorderFactory.createLineBorder(new Color(220, 220, 220), 1);
		Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		this.setBorder(BorderFactory.createCompoundBorder(linea, padding));
		
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));
		
		
		JPanel header = new JPanel(new GridLayout(1, 2, 10, 0));
		JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		header.setBackground(this.getBackground());
		leftHeader.setBackground(this.getBackground());
		rightHeader.setBackground(this.getBackground());
		
		JLabel statoAvanzamento = new JLabel(task.getStatoAvanzamento());
		statoAvanzamento.setOpaque(true);
		if(statoAvanzamento.getText().equals("Todo")) statoAvanzamento.setForeground(new Color(225, 228, 232));
		if(statoAvanzamento.getText().equals("In_progress")) statoAvanzamento.setForeground(new Color(255, 243, 205));
		if(statoAvanzamento.getText().equals("Done")) statoAvanzamento.setForeground(new Color(212, 237, 218));
		statoAvanzamento.setBackground(Color.WHITE);
		leftHeader.add(statoAvanzamento);
		
		LocalDate oggi = LocalDate.now();
		LocalDate scadenza = task.getScadenza();
		long giorniAllaScadenza = ChronoUnit.DAYS.between(oggi, scadenza);
		String giorniAllaScadenzaStr = String.valueOf(giorniAllaScadenza);
		JLabel dataScadenza = new JLabel(giorniAllaScadenzaStr + " days");
		if(giorniAllaScadenza <= 7) dataScadenza.setForeground(Color.red);
		if(giorniAllaScadenza > 7) dataScadenza.setForeground(Color.lightGray);
		rightHeader.add(dataScadenza);
		
		header.add(leftHeader);
		header.add(rightHeader);
		
		
		
		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		JTextArea descrizione = new JTextArea(task.getDescrizione());
		descrizione.setEditable(false);
		descrizione.setLineWrap(true);
		descrizione.setWrapStyleWord(true);
		descrizione.setOpaque(false);
		descrizione.setBorder(null);
		descrizione.setFocusable(false);
		descrizione.setForeground(Color.DARK_GRAY);
		JLabel str = new JLabel("Responsabili :");
		str.setForeground(Color.BLACK);
		
		body.add(descrizione);
		body.add(Box.createRigidArea(new Dimension(0, 20)));
		body.add(str);
		body.add(Box.createRigidArea(new Dimension(0, 20)));
		
		
		JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		footer.setBackground(this.getBackground());
		dettagliBtn = new JButton();
		
		try {
            
            ImageIcon iconaOriginale = new ImageIcon("src/dettagliIcon.png");
            Image iconaScalata = iconaOriginale.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            dettagliBtn.setIcon(new ImageIcon(iconaScalata));
        } catch (Exception ex) {
           
            dettagliBtn.setText("..."); 
        }
		
		dettagliBtn.setContentAreaFilled(false); 
        dettagliBtn.setBorderPainted(false);
        dettagliBtn.setFocusPainted(false);
		
        dettagliBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dettagliBtn.setToolTipText("Visualizza dettagli attività");
        
		dettagliBtn.addActionListener(this);
		
		footer.add(dettagliBtn, BorderLayout.WEST);
		
		this.add(header);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(descrizione);
		this.add(Box.createVerticalGlue());
		this.add(footer);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
        if (e.getSource() == dettagliBtn) {
           
            MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(this);
           
            new TaskDetailDialog(mainWindow, this.task, this.dp, this.dc);
        }
	}
	
}
