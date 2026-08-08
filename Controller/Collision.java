package Controller;

import Model.Block;
import Model.Pacman;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Collision {

    public static boolean checkCollision (int x, int y, Block p, Block b) {
        Rectangle playerRect = new Rectangle(x, y, p.getWidth(), p.getHeight());
        Rectangle blockRect = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        Boolean check = playerRect.intersects(blockRect);
        return check;
    }

    public static boolean checkCollision (Block p, Block b) {
        Rectangle playerRect = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        Rectangle blockRect = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        Boolean check = playerRect.intersects(blockRect);
        return check;
    }

    public static boolean checkVelocityCollision(int xVelocity, int yVelocity, Pacman player, ArrayList<Block> walls) {
        int tmpPlayerX = player.getX();
        int tmpPlayerY = player.getY();
        tmpPlayerX += xVelocity;
        tmpPlayerY += yVelocity;
        for (Block w: walls) {
            if (Collision.checkCollision(tmpPlayerX, tmpPlayerY, player, w))
                return true;
        }
        return false;
    }

    public static boolean checkDirectionCollision (char ch, Pacman player, ArrayList<Block> walls) {
        if (ch == 'U')
            return Collision.checkVelocityCollision (0, -8, player, walls);
        else if (ch == 'D')
            return Collision.checkVelocityCollision (0, 8, player, walls);
        else if (ch == 'R')
            return Collision.checkVelocityCollision(8, 0, player, walls);
        else if (ch == 'L')
            return Collision.checkVelocityCollision(-8, 0, player, walls);
        return false;
    }
}