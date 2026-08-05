package View;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MyTextField extends JTextField {
    public MyTextField (String text, int x, int y, int width, int height) {
        setText(text);
        setFont(new Font("Emulogic", Font.PLAIN, 22));
        setBounds(x, y, width, height);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}