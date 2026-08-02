package Model;

import java.awt.Image;

public class Block {
    public int x, y;
    public int width, height;
    public Image img;
    public int xVelocity, yVelocity;
    public char direction;
    public char preDirection;
    protected int firstX;
    protected int firstY;
    private int firstWidth;
    private int firstHeight;

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
}