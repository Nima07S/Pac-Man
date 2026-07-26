import javax.swing.ImageIcon;
import java.awt.Image;

public class MyImage {
    public Image wallImage;

    public Image pacmanRightImage;
    public Image pacmanLeftImage;
    public Image pacmanUpImage;
    public Image pacmanDownImage;

    public Image redGhostImage;
    public Image greenGhostImage;
    public Image orangeGhostImage;
    public Image pinkGhostImage;

    public void loadWall () {
        wallImage = new ImageIcon(getClass().getResource("/assets/wall.png")).getImage();
    }

    public void loadPacman () {
        pacmanRightImage = new ImageIcon(getClass().getResource("/assets/pacmanRight.png")).getImage();
        pacmanLeftImage = new ImageIcon(getClass().getResource("/assets/pacmanLeft.png")).getImage();
        pacmanUpImage = new ImageIcon(getClass().getResource("/assets/pacmanUp.png")).getImage();
        pacmanDownImage = new ImageIcon(getClass().getResource("/assets/pacmanDown.png")).getImage();
    }
    
    public void loadGhosts () {
        redGhostImage = new ImageIcon(getClass().getResource("/assets/redGhost.png")).getImage();
        greenGhostImage = new ImageIcon(getClass().getResource("/assets/greenGhost.png")).getImage();
        orangeGhostImage = new ImageIcon(getClass().getResource("/assets/orangeGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("/assets/pinkGhost.png")).getImage();
    }
}