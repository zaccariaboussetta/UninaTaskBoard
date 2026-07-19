package gui;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;

import controllers.ProgettoController;
import entities.Invito;

public class InvitiDialog extends JDialog {

	private ProgettoController progettoController;
	private ProgettiPanel parentPanel;

	public InvitiDialog(JFrame frame, ProgettiPanel parentPanel, String descrizione, boolean modale, ProgettoController progettoController){
		
		super(frame ,descrizione, modale);
		this.progettoController = progettoController;
		this.parentPanel = parentPanel;
		
		this.setTitle("Inviti ricevuti");
		this.setSize(300, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(frame);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
			
		}
		
}
