package View;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class MyLabel extends JLabel {
    public MyLabel (String text, int size, Color c, int x, int y, int width, int height) {
        setText(text);
        setFont(new Font("Arial", Font.BOLD, size));
        setForeground(c);
        setBounds(x, y, width, height);
    }

    public static void hideLabels (MyLabel ... labels) {
        for (MyLabel l: labels)
            l.setVisible(false);
    }

    public static void showLabels (MyLabel ... labels) {
        for (MyLabel l: labels)
            l.setVisible(true);
    }
}