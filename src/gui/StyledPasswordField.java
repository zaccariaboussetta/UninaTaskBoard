package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StyledPasswordField extends JPasswordField {

    public StyledPasswordField(int columns) {
        super(columns);
        
        
        this.setBackground(Color.WHITE);
        this.setForeground(Color.DARK_GRAY); 
        this.setFont(new Font("Arial", Font.PLAIN, 14));
        this.setCaretColor(new Color(0, 122, 255)); 

        
        Border lineaEsterna = BorderFactory.createLineBorder(new Color(200, 200, 200), 1);
        
        
        Border paddingInterno = BorderFactory.createEmptyBorder(4, 10, 4, 10); 
        
        this.setBorder(BorderFactory.createCompoundBorder(lineaEsterna, paddingInterno));
    }
}