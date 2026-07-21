import javax.swing.ImageIcon;
import java.awt.Image;

public class MyPhoto {
    public Image wallImage;

    public void loadWall () {
        wallImage = new ImageIcon(getClass().getResource("/assets/wall.png")).getImage();
    }
}