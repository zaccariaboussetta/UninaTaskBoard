package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StyledTextField extends JTextField {

    public StyledTextField(int columns) {
        super(columns);
        
        
        this.setBackground(Color.WHITE);
        this.setForeground(Color.DARK_GRAY); 
        this.setFont(new Font("SansSerif", Font.PLAIN, 14));
        this.setCaretColor(new Color(0, 122, 255)); 

        
        
        Border lineaEsterna = BorderFactory.createLineBorder(new Color(200, 200, 200), 1);
        
        
        Border paddingInterno = BorderFactory.createEmptyBorder(5, 15, 5, 15);
        
        
        this.setBorder(BorderFactory.createCompoundBorder(lineaEsterna, paddingInterno));
    }
}