package View;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    public MyFrame () {
        setTitle("PacMan");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(450, 150);
        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/pacmanIcon.png"));
        Image image = icon.getImage();
        setIconImage(image);
    }
}