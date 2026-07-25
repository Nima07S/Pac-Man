import java.awt.Image;

public class Pacman extends Block {
    public int xVelocity = 0;
    public int yVelocity = 0;
    public char direction;
    public char preDirection;

    public Pacman (int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
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

    public void updateDirection (char ch) {
        preDirection = direction;
        direction = ch;
        updateVelocity();
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
}