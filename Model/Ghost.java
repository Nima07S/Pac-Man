package Model;

import Controller.Collision;

import java.awt.Image;
import java.util.Random;
import java.util.ArrayList;

public class Ghost extends Block {
    private char[] directions = {'R', 'L', 'U', 'D'};
    private Random randDir = new Random();

    public Ghost (int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
        firstX = x;
        firstY = y;
        generateRandomDir();
    }

    public static void ghostsMove (ArrayList<Ghost> ghosts, ArrayList<Block> walls) {
        for (Ghost g: ghosts) {
            g.updateVelocity();
            g.x += g.xVelocity;
            g.y += g.yVelocity;
            for (Block w: walls) {
                if (Collision.checkCollision(g, w)) {
                    g.x -= g.xVelocity;
                    g.y -= g.yVelocity;
                    g.generateRandomDir();
                    break;
                }
            }
        }
    }

    public void generateRandomDir () {
        direction = directions[randDir.nextInt(4)];
    }

    public void resetPosition () {
        x = firstX;
        y = firstY;
    }
}