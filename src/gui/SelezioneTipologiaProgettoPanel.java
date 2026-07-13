package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SelezioneTipologiaProgettoPanel extends JPanel implements ActionListener {
    
    private CreateProjectFrame parentFrame;
    private JButton avantiButton;
    private JButton annullaButton;
    private JCheckBox checkSviluppo;
    private JCheckBox checkGenerico;
    private JCheckBox checkEsame;
    
    public SelezioneTipologiaProgettoPanel(Dimension dimension, CreateProjectFrame parentFrame) {
        
        this.parentFrame = parentFrame;
        this.setPreferredSize(dimension);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        JPanel leftPanel1 = new JPanel();
        leftPanel1.setPreferredSize(new Dimension(400, dimension.height));
        leftPanel1.setLayout(new BorderLayout());
        
        ImageIcon imageLeft = new ImageIcon("src/leftPanelIcon.jpg");
        Image scaledImage = imageLeft.getImage().getScaledInstance(400, dimension.height, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        
        leftPanel1.add(imageLabel);
        
        JPanel rightPanel1 = new JPanel();
        rightPanel1.setPreferredSize(new Dimension(400, dimension.height));
        rightPanel1.setBackground(Color.white);
        rightPanel1.setLayout(new BorderLayout());
        rightPanel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titoloLabel = new JLabel("<html><b>Selezionare tipologia di progetto.</b><br><br>"
                + "Si scelga tra le seguenti opzioni, notando all'utente la possibilità di poter realizzare un progetto di tipo misto.</html>");
        titoloLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.setBackground(Color.white);
        
        checkGenerico = new JCheckBox("Generico");
        checkGenerico.setBackground(Color.white);
        checkGenerico.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea descrizionProgettogenerico = new JTextArea();
        descrizionProgettogenerico.setText("Un progetto generico che non sia né per lo sviluppo di applicativi "
                + "né un progetto per la preparazione di un esame.");
        descrizionProgettogenerico.setEditable(false);
        descrizionProgettogenerico.setOpaque(false);
        descrizionProgettogenerico.setLineWrap(true);
        descrizionProgettogenerico.setWrapStyleWord(true);
        descrizionProgettogenerico.setBorder(BorderFactory.createEmptyBorder(0, 22, 0, 0));
        descrizionProgettogenerico.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        checkSviluppo = new JCheckBox("Sviluppo applicativo");
        checkSviluppo.setBackground(Color.white);
        checkSviluppo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea descrizionProgettoSviluppo = new JTextArea();
        descrizionProgettoSviluppo.setText("Un progetto pertinente alla realizzazione di un software, una base di dati o simili.");
        descrizionProgettoSviluppo.setEditable(false);
        descrizionProgettoSviluppo.setOpaque(false);
        descrizionProgettoSviluppo.setLineWrap(true);
        descrizionProgettoSviluppo.setWrapStyleWord(true);
        descrizionProgettoSviluppo.setBorder(BorderFactory.createEmptyBorder(0, 22, 0, 0));
        descrizionProgettoSviluppo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        checkEsame = new JCheckBox("Preparazione esame");
        checkEsame.setBackground(Color.white);
        checkEsame.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea descrizionProgettoEsame = new JTextArea();
        descrizionProgettoEsame.setText("Un progetto che riguarda la gestione delle attività rivolte alla preparazione di un esame specifico.");
        descrizionProgettoEsame.setEditable(false);
        descrizionProgettoEsame.setOpaque(false);
        descrizionProgettoEsame.setLineWrap(true);
        descrizionProgettoEsame.setWrapStyleWord(true);
        descrizionProgettoEsame.setBorder(BorderFactory.createEmptyBorder(0, 22, 0, 0));
        descrizionProgettoEsame.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        checkBoxPanel.add(checkGenerico);
        checkBoxPanel.add(descrizionProgettogenerico);
        checkBoxPanel.add(Box.createVerticalStrut(20));
        
        checkBoxPanel.add(checkSviluppo);
        checkBoxPanel.add(descrizionProgettoSviluppo);
        checkBoxPanel.add(Box.createVerticalStrut(20)); 
        
        checkBoxPanel.add(checkEsame);
        checkBoxPanel.add(descrizionProgettoEsame);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBackground(Color.white);
        
        avantiButton = new JButton("Avanti >");
        avantiButton.addActionListener(this);
        
        annullaButton = new JButton("Annulla");
        annullaButton.addActionListener(this);
    
        buttonsPanel.add(annullaButton);
        buttonsPanel.add(avantiButton);
        
        rightPanel1.add(titoloLabel, BorderLayout.NORTH);
        rightPanel1.add(checkBoxPanel, BorderLayout.CENTER);
        rightPanel1.add(buttonsPanel, BorderLayout.SOUTH);
        
        this.add(leftPanel1);
        this.add(rightPanel1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == avantiButton) {
            
            if(checkSviluppo.isSelected() && checkEsame.isSelected()) parentFrame.showPanel("SVILUPPO ESAME");
            
            else if(checkSviluppo.isSelected()) parentFrame.showPanel("SVILUPPO");
            
            else if(checkEsame.isSelected()) parentFrame.showPanel("ESAME");
            
            else if(checkGenerico.isSelected()) parentFrame.showPanel("GENERICO");
            
            else { JOptionPane.showMessageDialog(null, "Seleziona la tipologia di progetto","", JOptionPane.INFORMATION_MESSAGE); }
            
        }
        
        if(e.getSource() == annullaButton) {
            parentFrame.dispose(); 
        }
    }
}
