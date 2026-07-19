package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import controllers.DashboardController;
import entities.StatisticaMembroDTO;

public class ReportDialog extends JDialog {

    public ReportDialog(JFrame parent, DashboardController dc) {
        super(parent, "Statistiche di Progetto", true);
        this.setSize(900, 550);
        this.setLocationRelativeTo(parent);
        this.setLayout(new BorderLayout());

        
        JPanel headerPanel = new JPanel(new GridLayout(1, 2));
        headerPanel.setBackground(new Color(52, 73, 94));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel lblTotale = new JLabel("Totale Attività: " + dc.getTotaleAttivita());
        lblTotale.setForeground(Color.WHITE);
        lblTotale.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel lblSviluppo = new JLabel("Di cui Sviluppo: " + dc.getTotaleAttivitaSviluppo());
        lblSviluppo.setForeground(Color.WHITE);
        lblSviluppo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblSviluppo.setHorizontalAlignment(SwingConstants.RIGHT);

        headerPanel.add(lblTotale);
        headerPanel.add(lblSviluppo);
        this.add(headerPanel, BorderLayout.NORTH);

        
        JPanel chartPanelContainer = new JPanel(new GridLayout(1, 2, 10, 0));
        chartPanelContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        chartPanelContainer.setBackground(Color.WHITE);

        
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("To Do", dc.getConteggioAttivitaPerStato("Todo"));
        pieDataset.setValue("In Progress", dc.getConteggioAttivitaPerStato("In_progress"));
        pieDataset.setValue("Done", dc.getConteggioAttivitaPerStato("Done"));

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Stato di Avanzamento", 
                pieDataset,             
                true,                   
                true,                   
                false                   
        );
        
        
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSectionPaint("To Do", new Color(220, 53, 69));      
        plot.setSectionPaint("In Progress", new Color(255, 193, 7)); 
        plot.setSectionPaint("Done", new Color(40, 167, 69));        
        plot.setBackgroundPaint(Color.WHITE);

        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        chartPanelContainer.add(pieChartPanel);


     
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        
        
        java.util.ArrayList<StatisticaMembroDTO> statMembri = dc.getTaskCompletatePerMembro();
        
        
        for (StatisticaMembroDTO sm : statMembri) {
            barDataset.addValue(sm.getTaskCompletate(), "Completate", sm.getNomeMembro());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Task Completate dai Membri", 
                "Membro",                     
                "Numero Task",                
                barDataset,                   
               PlotOrientation.VERTICAL,     
                false,                        
                true,                         
                false                         
        );
        barChart.getPlot().setBackgroundPaint(new Color(245, 245, 245));

        ChartPanel barChartPanel = new ChartPanel(barChart);
        chartPanelContainer.add(barChartPanel);


        this.add(chartPanelContainer, BorderLayout.CENTER);
        this.setVisible(true);
    }
}