import java.awt.Rectangle;

public class Collision {

    public static boolean checkCollision (int x, int y, Block p, Block b) {
        Rectangle playerRect = new Rectangle(x, y, p.width, p.height);
        Rectangle blockRect = new Rectangle(b.x, b.y, b.width, b.height);
        Boolean check = playerRect.intersects(blockRect);
        return check;
    }
}