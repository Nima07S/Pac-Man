import java.awt.Image;
import java.util.ArrayList;;

public class Pellet extends Block {
    public Pellet (int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
    }

    public static void loadPellets (ArrayList<Block> pellets, Map map) {
        for (int i=0; i<map.getMaze().length; i++) {
            for (int j=0; j<map.getMaze()[i].length(); j++) {
                if (map.getMaze()[i].charAt(j) == ' ') {
                    Block pellet = new Block(j*32+13, i*32+13, 6, 6, null);
                        pellets.add(pellet);
                }
            }
        }
    }
}