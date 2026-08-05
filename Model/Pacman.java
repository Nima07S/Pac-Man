package Model;

import Controller.Collision;

import java.awt.Image;
import java.util.ArrayList;

public class Pacman extends Block {
    private static Block eatenPellet;
    private static int eatenPelletCount;
    private ScoreManager scoreManager;

    public Pacman (int x, int y, int width, int height, Image img, ScoreManager sm) {
        super(x, y, width, height, img);
        firstX = x;
        firstY = y;
        eatenPelletCount = 0;
        scoreManager = sm;
    }

    public void pacmanPelletCollision (Block p, ArrayList<Block> foods) {
        for (Block f: foods) {
            if (Collision.checkCollision(p, f)) {
                eatenPellet = f;
                scoreManager.addScore(10);
                break;
            }
        }
        foods.remove(eatenPellet);
        eatenPellet = null;
    }

    @Override
    public void resetPosition () {
        x = firstX;
        y = firstY;
        stop();
    }

    public void actualMove (ArrayList<Block> walls, boolean isGameStarted) {
        if (isGameStarted) {
            if (!Collision.checkVelocityCollision(xVelocity, yVelocity, this, walls)) {
                x += xVelocity;
                y += yVelocity;
            }
            else
                stop();
        }
    }

    public void teleporting () {
        if (x + width/2 == 0 && direction == 'L')
            x = width*19 - x;
        else if (x == width*19 - width/2 && direction == 'R')
            x =  -1 * width/2;
    }
}