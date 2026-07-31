import java.awt.Image;
import java.util.ArrayList;

public class Pacman extends Block {
    public static Block eatenPellet;
    public static int eatenPelletCount;
    public static int c = 0;

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
                c++;
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
}