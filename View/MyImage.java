package View;

import Model.Pacman;

import javax.swing.ImageIcon;
import java.awt.Image;

public class MyImage {
    private Image wallImage;

    private Image pacmanRightImage;
    private Image pacmanLeftImage;
    private Image pacmanUpImage;
    private Image pacmanDownImage;

    private Image redGhostImage;
    private Image greenGhostImage;
    private Image orangeGhostImage;
    private Image pinkGhostImage;

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

    public void setPacManImage (Pacman player) {
        if (player.getDirection() == 'U')
            player.setImage(pacmanUpImage);
        else if (player.getDirection() == 'D')
            player.setImage(pacmanDownImage);
        else if (player.getDirection() == 'R')
            player.setImage(pacmanRightImage);
        else if (player.getDirection() == 'L')
            player.setImage(pacmanLeftImage);
    }

    public Image getRightPacmanImage () {
        return pacmanRightImage;
    }
    public Image getWallImage () {
        return wallImage;
    }
    public Image getRedGhostImage () {
        return redGhostImage;
    }
    public Image getGreenGhostImage () {
        return greenGhostImage;
    }
    public Image getOrangeGhostImage () {
        return orangeGhostImage;
    }
    public Image getPinkGhostImage () {
        return pinkGhostImage;
    }
}