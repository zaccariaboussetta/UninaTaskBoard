package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StyledTextField extends JTextField {

    public StyledTextField(int columns) {
        super(columns);
        
        // 1. Colori e Font
        this.setBackground(Color.WHITE);
        this.setForeground(Color.DARK_GRAY); // Testo grigio scuro, più elegante del nero assoluto
        this.setFont(new Font("SansSerif", Font.PLAIN, 14));
        this.setCaretColor(new Color(0, 122, 255)); // Cursore blu "Apple"

        // 2. Bordo e Spaziatura (Padding)
        // Crea una linea grigia chiara attorno alla casella
        Border lineaEsterna = BorderFactory.createLineBorder(new Color(200, 200, 200), 1);
        
        // Crea uno spazio interno di 10 pixel sopra/sotto e 15 pixel a destra/sinistra
        Border paddingInterno = BorderFactory.createEmptyBorder(5, 15, 5, 15);
        
        // Fonde i due bordi
        this.setBorder(BorderFactory.createCompoundBorder(lineaEsterna, paddingInterno));
    }
}