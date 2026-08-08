package Model;

import View.MyImage;
import java.util.ArrayList;

public class Map {
    private final int SIZE = 32;
    private MyImage images;
    private ArrayList<Block> walls;
    private ArrayList<Block> pellets;
    private ArrayList<Ghost> ghosts;

    private String[] maze = {
        "*******************",
        "xxxxxxxxxxxxxxxxxxx",
        "x        x        x",
        "x xx xxx x xxx xx x",
        "x      x   x      x",
        "x xx x xx xx x xx x",
        "x    x       x    x",
        "xxxx xx xxx xx xxxx",
        "---x           x---",
        "xxxx x xxRxx x xxxx",
        "     x  G O  x     ",
        "xxxx x xxPxx x xxxx",
        "---x           x---",
        "xxxx xxx x xxx xxxx",
        "x        x        x",
        "x xx xxx x xxx xx x",
        "x x   x  p  x   x x",
        "x x x x xxx x x x x",
        "x   x    x    x   x",
        "x xxxxxx x xxxxxx x",
        "x                 x",
        "xxxxxxxxxxxxxxxxxxx"
    };

    public Map (MyImage imgs, ArrayList<Block> p, ArrayList<Block> w, ArrayList<Ghost> g) {
        this.images = imgs;
        this.ghosts = g;
        this.pellets = p;
        this.walls = w;
    }

    public String[] getMaze () {
        return maze;
    }

    public void loadMaze () {
        for (int i=0; i<maze.length; i++) {
            for (int j=0; j<getMaze()[i].length(); j++) {
                switch (getMaze()[i].charAt(j)) {
                    case 'x':
                        Block wall = new Block(32*j, 32*i, SIZE, SIZE, images.getWallImage());
                        walls.add(wall);
                        break;
                    case ' ':
                        Block pellet = new Block(j*32+13, i*32+13, 6, 6, null);
                        pellets.add(pellet);
                        break;
                    case 'R':
                        Ghost redGhost = new Ghost(SIZE*9, SIZE*9, SIZE, SIZE, images.getRedGhostImage());
                        ghosts.add(redGhost);
                        break;
                    case 'G':
                        Ghost greenGhost = new Ghost(SIZE*8, SIZE*10, SIZE, SIZE, images.getGreenGhostImage());
                        ghosts.add(greenGhost);
                        break;
                    case 'O':
                        Ghost orangeGhost = new Ghost(SIZE*10, SIZE*10, SIZE, SIZE, images.getOrangeGhostImage());
                        ghosts.add(orangeGhost);
                        break;
                    case 'P':
                        Ghost pinkGhost = new Ghost(SIZE*9, SIZE*11, SIZE, SIZE, images.getPinkGhostImage());
                        ghosts.add(pinkGhost);
                }
            }
        }
    }

    public void loadPellets () {
        for (int i=0; i<getMaze().length; i++) {
            for (int j=0; j<getMaze()[i].length(); j++) {
                if (getMaze()[i].charAt(j) == ' ') {
                    Block pellet = new Block(j*32+13, i*32+13, 6, 6, null);
                        pellets.add(pellet);
                }
            }
        }
    }
}