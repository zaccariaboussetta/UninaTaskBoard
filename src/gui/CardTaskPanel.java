package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.DashboardController;
import entities.Attivita;

public class CardTaskPanel extends JPanel{

	public CardTaskPanel(ArrayList<Attivita> listTasks, String titolo, DashboardPanel dp, DashboardController dc) {
		
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(this.getBackground());
		JLabel titoloHeader = new JLabel(titolo);
		titoloHeader.setForeground(Color.black);
		String num = String.valueOf(listTasks.size());
		JLabel numTasks = new JLabel(num);
		numTasks.setForeground(Color.LIGHT_GRAY);
		
		header.add(titoloHeader);
		header.add(numTasks);
		
		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(this.getBackground());
		
		for(Attivita t : listTasks) {
			
			body.add(new CardTask(t, dp, dc));
			body.add(Box.createRigidArea(new Dimension(0, 15)));
			
		}
		
		JScrollPane scrollPane = new JScrollPane(body);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().setBackground(this.getBackground());
		
		this.add(header, BorderLayout.NORTH);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
}
