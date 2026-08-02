import java.awt.Image;
import java.util.ArrayList;

public class Pacman extends Block {
    public static Block eatenPellet;
    public static int eatenPelletCount;

    public Pacman (int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
        firstX = x;
        firstY = y;
        eatenPelletCount = 0;
    }

    public void pacmanPelletCollision (Block p, ArrayList<Block> foods) {
        for (Block f: foods) {
            if (Collision.checkCollision(p, f)) {
                eatenPellet = f;
                ScoreManager.addScore(10);
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
}