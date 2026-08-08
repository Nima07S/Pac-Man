package Model;

import java.awt.Image;

public class Block {
    protected int x, y;
    protected int firstX, firstY;
    protected int width, height;
    protected int xVelocity, yVelocity;
    protected int firstWidth;
    protected int firstHeight;
    protected Image img;
    protected char direction;
    protected char preDirection;

    public Block (int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        firstX = x;
        firstY = y;
        this.firstHeight = height;
        this.firstWidth = width;
    }

    public void updateVelocity () {
        if (direction == 'U')
            moveUp();
        else if (direction == 'D')
            moveDown();
        else if (direction == 'L')
            moveLeft();
        else if (direction == 'R')
            moveRight();
        else if (direction == 'S')
            stop();
    }

    public void moveUp () {
        xVelocity = 0;
        yVelocity = -8;
    }
    public void moveDown () {
        xVelocity = 0;
        yVelocity = 8;
    }
    public void moveRight () {
        xVelocity = 8;
        yVelocity = 0;
    }
    public void moveLeft () {
        xVelocity = -8;
        yVelocity = 0;
    }
    public void stop () {
        xVelocity = 0;
        yVelocity = 0;
    }

    public void updateDirection (char ch) {
        preDirection = direction;
        direction = ch;
        updateVelocity();
    }

    public void resetPosition () {
        x = firstX;
        y = firstY;
        width = firstWidth;
        height = firstHeight;
    }

    public int getX () {
        return x;
    }
    public int getY () {
        return y;
    }
    public int getWidth () {
        return width;
    }
    public int getHeight () {
        return height;
    }
    public int getXVelocity () {
        return xVelocity;
    }
    public int getYVelocity () {
        return yVelocity;
    }
    public Image getImage () {
        return img;
    }
    public void setImage (Image image) {
        img = image;
    }
    public char getDirection () {
        return direction;
    }
    public void setDirection (char c) {
        direction = c;
    }
    public char getPreDirection () {
        return preDirection;
    }
}