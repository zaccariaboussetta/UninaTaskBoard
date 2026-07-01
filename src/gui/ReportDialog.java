package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import entities.Attivita;
import entities.AttivitaSviluppo;
import entities.Membro;

public class ReportDialog extends JDialog {

	public ReportDialog(ArrayList<Attivita> tasks, ArrayList<Membro> assignments) {
		this.setTitle("Report Progetto");
		this.setSize(700, 500);
		this.setLayout(new BorderLayout());
		this.setModal(true); 

		int totaleAttivita = tasks.size();
		int completate = 0;
		int inCorso = 0;
		int nonIniziate = 0;
		int attivitaSviluppo = 0;

		ArrayList<String> nomiMembri = new ArrayList<>();
		ArrayList<Integer> taskCompletatePerMembro = new ArrayList<>();

		for (int i = 0; i < tasks.size(); i++) {
			Attivita t = tasks.get(i);

			if (t.getStatoAvanzamento().equals("Done")) {
				completate++;
				
				Membro m = assignments.get(i);
				if (m != null) {
					String nome = m.getUtente().getNome() + " " + m.getUtente().getCognome();
					
					boolean trovato = false;
					for (int j = 0; j < nomiMembri.size(); j++) {
						if (nomiMembri.get(j).equals(nome)) {
							int conteggioAttuale = taskCompletatePerMembro.get(j);
							taskCompletatePerMembro.set(j, conteggioAttuale + 1);
							trovato = true;
							break;
						}
					}
					
					if (!trovato) {
						nomiMembri.add(nome);
						taskCompletatePerMembro.add(1);
					}
				}
				
			} else if (t.getStatoAvanzamento().equals("In_Progress")) {
				inCorso++;
			} else if (t.getStatoAvanzamento().equals("Todo")) {
				nonIniziate++;
			}

			if (t instanceof AttivitaSviluppo) {
				attivitaSviluppo++;
			}
		}

		JTextArea reportText = new JTextArea();
		reportText.setEditable(false);
		reportText.append("=== REPORT STATISTICO ===\n\n");
		reportText.append("Totale Attività: " + totaleAttivita + "\n");
		reportText.append("- Completate (Done): " + completate + "\n");
		reportText.append("- In Corso (In_Progress): " + inCorso + "\n");
		reportText.append("- Non Iniziate (Todo): " + nonIniziate + "\n\n");
		
		reportText.append("Attività di Sviluppo totali: " + attivitaSviluppo + "\n\n");
		
		reportText.append("=== TASK COMPLETATE PER MEMBRO ===\n");
		for (int i = 0; i < nomiMembri.size(); i++) {
			reportText.append(nomiMembri.get(i) + ": " + taskCompletatePerMembro.get(i) + " task\n");
		}
		
		JScrollPane textScroll = new JScrollPane(reportText);
		textScroll.setPreferredSize(new Dimension(300, 0));
		this.add(textScroll, BorderLayout.WEST);

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Non Iniziate", nonIniziate);
		dataset.setValue("In Corso", inCorso);
		dataset.setValue("Completate", completate);

		JFreeChart pieChart = ChartFactory.createPieChart(
				"Stato Avanzamento Attività", 
				dataset,                      
				true,                         
				true,                         
				false                         
		);

		ChartPanel chartPanel = new ChartPanel(pieChart);
		this.add(chartPanel, BorderLayout.CENTER);
		
		this.setLocationRelativeTo(null);
	}
}
