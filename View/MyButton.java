package View;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;

public class MyButton extends JButton {
    private static ArrayList<MyButton> buttons = new ArrayList<>();
    
    public MyButton (String text, int size, int x, int y, int width, int height, MyPanel panel) {
        setText(text);
        setFont(new Font("Emulogic", Font.BOLD, size));
        setBounds(x, y, width, height);
        addActionListener(panel);
        setFocusPainted(false);
        setBorderPainted(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        buttons.add(this);
    }

    public static void hideButtons () {
        for (MyButton button: buttons)
            button.setVisible(false);
    }

    public static void showButtons () {
        for (MyButton button: buttons)
            button.setVisible(true);
    }
}