package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import entities.Progetto;

public class ProgettoRadioButton extends JRadioButton implements ActionListener{

	private int idProg;
	private String descrizione;
	private LocalDate creazione;
	private String listaMembri;
	private ProgettiPanel progettiPanel;
	
	public ProgettoRadioButton(ProgettiPanel progettiPanel, Progetto prog, String lm) {
		
		super(prog.getNome());
		
		this.progettiPanel = progettiPanel;
		this.idProg = prog.getIdProgetto();
		this.descrizione = prog.getDescrizione();
		this.creazione = prog.getDataCreazione();
		this.listaMembri = lm;
		
		this.setMargin(new Insets(10, 20, 10, 20));
		
		this.setFont(new Font("Arial", Font.PLAIN, 14)); 
		this.setForeground(Color.BLACK);                 
		this.setBackground(Color.WHITE);                
		this.setFocusPainted(false);                    
		this.setOpaque(false);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		ImageIcon folderStatica = new ImageIcon("src/folderStatica.png");
        Image scaledFolderIcon = folderStatica.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        folderStatica = new ImageIcon(scaledFolderIcon);
        
        ImageIcon folderHover = new ImageIcon("src/folderHover.png");
        Image scaledFolderHHoverIcon = folderHover.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        folderHover = new ImageIcon(scaledFolderHHoverIcon);
        
        ImageIcon folderSelezionata = new ImageIcon("src/folderSelezionata.png");
        Image scaledFolderSelezioanaIcon = folderSelezionata.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        folderSelezionata = new ImageIcon(scaledFolderSelezioanaIcon);
        
        this.setIcon(folderStatica);
        this.setRolloverEnabled(true);
        this.setRolloverIcon(folderHover);
        this.setSelectedIcon(folderSelezionata);
       
        this.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e ) {
		
		progettiPanel.selectedProg(idProg, descrizione, creazione, listaMembri);
		
	}
	
}

