import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    final int SIZE = 32;
    int row = 21, column = 19;
    int width = SIZE*column;
    int height = SIZE*row;

    MyPhoto photos = new MyPhoto();
    Map map = new Map();

    ArrayList<Block> walls = new ArrayList<>();
    ArrayList<Block> pellets = new ArrayList<>();
    ArrayList<Block> ghosts = new ArrayList<>();
    Pacman player = new Pacman(SIZE*9, SIZE*15, 32, 32, photos.pacmanRightImage);

    public MyPanel () {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);

        photos.loadWall();
        photos.loadPacman();
        photos.loadGhosts();
        
        for (int i=0; i<map.maze.length; i++) {
            for (int j=0; j<map.maze[i].length(); j++) {
                switch (map.maze[i].charAt(j)) {
                    case 'x':
                        Block wall = new Block(32*j, 32*i, SIZE, SIZE, photos.wallImage);
                        walls.add(wall);
                        break;
                    case ' ':
                        Block pellet = new Block(j*32+12, i*32+12, 8, 8, null);
                        pellets.add(pellet);
                        break;
                    case 'R':
                        Block redGhost = new Block(SIZE*9, SIZE*8, SIZE, SIZE, photos.redGhostImage);
                        ghosts.add(redGhost);
                        break;
                    case 'G':
                        Block greenGhost = new Block(SIZE*8, SIZE*9, SIZE, SIZE, photos.greenGhostImage);
                        ghosts.add(greenGhost);
                        break;
                    case 'O':
                        Block orangeGhost = new Block(SIZE*10, SIZE*9, SIZE, SIZE, photos.orangeGhostImage);
                        ghosts.add(orangeGhost);
                        break;
                    case 'P':
                        Block pinkGhost = new Block(SIZE*9, SIZE*10, SIZE, SIZE, photos.pinkGhostImage);
                        ghosts.add(pinkGhost);
                }
            }
        }
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g) {
        for (Block w: walls) {
            g.drawImage(w.img, w.x, w.y, w.width, w.height, null);
        }

        g.drawImage(photos.pacmanRightImage, player.x, player.y, player.width, player.height, null);
        
        g.setColor(Color.WHITE);
        for (Block p: pellets) {
            g.fillRect(p.x, p.y, p.width, p.height);
        }

        for (Block G: ghosts) 
            g.drawImage(G.img, G.x, G.y, G.width, G.height, null);
    }
}