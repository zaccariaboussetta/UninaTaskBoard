package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StyledPasswordField extends JPasswordField {

    public StyledPasswordField(int columns) {
        super(columns);
        
        // 1. Colori e Font
        this.setBackground(Color.WHITE);
        this.setForeground(Color.DARK_GRAY); 
        this.setFont(new Font("Arial", Font.PLAIN, 14));
        this.setCaretColor(new Color(0, 122, 255)); // Stesso colore del cursore

        // 2. Bordo e Spaziatura (Padding)
        Border lineaEsterna = BorderFactory.createLineBorder(new Color(200, 200, 200), 1);
        
        // ATTENZIONE: Usa qui gli stessi identici numeri che hai in StyledTextField
        Border paddingInterno = BorderFactory.createEmptyBorder(4, 10, 4, 10); 
        
        this.setBorder(BorderFactory.createCompoundBorder(lineaEsterna, paddingInterno));
    }
}