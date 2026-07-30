import java.awt.Image;
import java.util.ArrayList;

public class Pacman extends Block {
    public static Block eatenPellet;
    public static int eatenPelletCount;

    public Pacman (int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
        firstX = 9*32;
        firstY = 16*32;
        eatenPelletCount = 0;
    }

    public void pacmanPelletCollision (Block p, ArrayList<Block> foods) {
        for (Block f: foods) {
            if (Collision.checkCollision(p, f)) {
                eatenPellet = f;
                eatenPelletCount++;
                break;
            }
        }
        foods.remove(eatenPellet);
    }

    @Override
    public void resetPosition () {
        x = firstX;
        y = firstY;
        stop();
    }
}