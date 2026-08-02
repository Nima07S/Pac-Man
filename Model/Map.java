package Model;

import java.util.ArrayList;
import java.awt.Image;

public class Map {
    private final int SIZE = 32;

    public String[] maze = {
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

    public String[] getMaze () {
        return maze;
    }

    public void loadMaze (ArrayList<Block> walls, ArrayList<Ghost> ghosts, ArrayList<Block> pellets, Image wI, Image rI, Image gI, Image oI, Image pI) {
        for (int i=0; i<getMaze().length; i++) {
            for (int j=0; j<getMaze()[i].length(); j++) {
                switch (getMaze()[i].charAt(j)) {
                    case 'x':
                        Block wall = new Block(32*j, 32*i, SIZE, SIZE, wI);
                        walls.add(wall);
                        break;
                    case ' ':
                        Block pellet = new Block(j*32+13, i*32+13, 6, 6, null);
                        pellets.add(pellet);
                        break;
                    case 'R':
                        Ghost redGhost = new Ghost(SIZE*9, SIZE*9, SIZE, SIZE, rI);
                        ghosts.add(redGhost);
                        break;
                    case 'G':
                        Ghost greenGhost = new Ghost(SIZE*8, SIZE*10, SIZE, SIZE, gI);
                        ghosts.add(greenGhost);
                        break;
                    case 'O':
                        Ghost orangeGhost = new Ghost(SIZE*10, SIZE*10, SIZE, SIZE, oI);
                        ghosts.add(orangeGhost);
                        break;
                    case 'P':
                        Ghost pinkGhost = new Ghost(SIZE*9, SIZE*11, SIZE, SIZE, pI);
                        ghosts.add(pinkGhost);
                }
            }
        }
    }
}