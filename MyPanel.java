import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    int pixelSize = 32;
    int row = 21, column = 19;
    int width = pixelSize*column;
    int height = pixelSize*row;

    MyPhoto photos = new MyPhoto();
    Map map = new Map();
    ArrayList<Block> walls = new ArrayList<>();

    public MyPanel () {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);

        photos.loadWall();
        
        for (int i=0; i<map.maze.length; i++) {
            for (int j=0; j<map.maze[i].length(); j++) {
                if (map.maze[i].charAt(j) == 'x') {
                    Block wall = new Block(32*j, 32*i, 32, 32, photos.wallImage);
                    walls.add(wall);
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
        for (Block wall: walls) {
            g.drawImage(wall.img, wall.x, wall.y, wall.width, wall.height, null);
        }
    }
}