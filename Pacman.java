import java.awt.Image;
import java.util.ArrayList;

public class Pacman extends Block {

    public Pacman (int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
    }

    public void pacmanPelletCollision (Block p, ArrayList<Block> foods) {
        for (Block f: foods) {
            if (Collision.checkCollision(p, f)) {
                f.width = 0;
                f.height = 0;
            }
        }
    }
}