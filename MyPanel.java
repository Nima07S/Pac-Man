import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements ActionListener, KeyListener {
    final int SIZE = 32;
    int row = 21, column = 19;
    int width = SIZE*column;
    int height = SIZE*row;

    JButton startButton;
    JButton exitButton;

    MyImage images = new MyImage();
    Map map = new Map();

    ArrayList<Block> walls = new ArrayList<>();
    ArrayList<Block> pellets = new ArrayList<>();
    ArrayList<Block> ghosts = new ArrayList<>();

    Pacman player = new Pacman(SIZE*9, SIZE*15, 32, 32, null);
    
    Timer loop;

    public MyPanel () {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        
        setLayout(null);

        startButton = new JButton();
        startButton.setText("START");
        startButton.setBounds(240, 150, 120, 80);
        startButton.addActionListener(this);

        exitButton = new JButton();
        exitButton.setText("EXIT");
        exitButton.setBounds(240, 350, 120, 80);
        exitButton.addActionListener(this);

        add(startButton);
        add(exitButton);
        
        setFocusable(true);
        addKeyListener(this);

        images.loadWall();
        images.loadPacman();
        images.loadGhosts();

        player.img = images.pacmanRightImage;
        
        for (int i=0; i<map.maze.length; i++) {
            for (int j=0; j<map.maze[i].length(); j++) {
                switch (map.maze[i].charAt(j)) {
                    case 'x':
                        Block wall = new Block(32*j, 32*i, SIZE, SIZE, images.wallImage);
                        walls.add(wall);
                        break;
                    case ' ':
                        Block pellet = new Block(j*32+13, i*32+13, 6, 6, null);
                        pellets.add(pellet);
                        break;
                    case 'R':
                        Block redGhost = new Block(SIZE*9, SIZE*8, SIZE, SIZE, images.redGhostImage);
                        ghosts.add(redGhost);
                        break;
                    case 'G':
                        Block greenGhost = new Block(SIZE*8, SIZE*9, SIZE, SIZE, images.greenGhostImage);
                        ghosts.add(greenGhost);
                        break;
                    case 'O':
                        Block orangeGhost = new Block(SIZE*10, SIZE*9, SIZE, SIZE, images.orangeGhostImage);
                        ghosts.add(orangeGhost);
                        break;
                    case 'P':
                        Block pinkGhost = new Block(SIZE*9, SIZE*10, SIZE, SIZE, images.pinkGhostImage);
                        ghosts.add(pinkGhost);
                }
            }
        }

        // 1000 ms / 40 = 25 FPS
        loop = new Timer(40, this);
        loop.start();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g) {
        for (Block w: walls) {
            g.drawImage(w.img, w.x, w.y, w.width, w.height, null);
        }

        g.drawImage(player.img, player.x, player.y, player.width, player.height, null);
        
        g.setColor(Color.WHITE);
        for (Block p: pellets) {
            g.fillRect(p.x, p.y, p.width, p.height);
        }

        for (Block G: ghosts) 
            g.drawImage(G.img, G.x, G.y, G.width, G.height, null);
    }

    public boolean checkDirectionCollision (char ch) {
        if (ch == 'U')
            return checkVelocityCollision (0, -8);
        else if (ch == 'D')
            return checkVelocityCollision (0, 8);
        else if (ch == 'R')
            return checkVelocityCollision(8, 0);
        else if (ch == 'L')
            return checkVelocityCollision(-8, 0);
        return false;
    }
   
    private boolean checkVelocityCollision(int xVelocity, int yVelocity) {
        int tmpPlayerX = player.x;
        int tmpPlayerY = player.y;
        tmpPlayerX += xVelocity;
        tmpPlayerY += yVelocity;
        for (Block w: walls) {
            if (Collision.checkCollision(tmpPlayerX, tmpPlayerY, player, w))
                return true;
        }
        return false;
    }

    public void move () {
        if (!checkVelocityCollision(player.xVelocity, player.yVelocity)) {
            player.x += player.xVelocity;
            player.y += player.yVelocity;
        }
        else
            player.stop();

        if (player.x + player.width/2 == 0 && player.direction == 'L')
            player.x = width - player.x;
        else if (player.x == width - player.width/2 && player.direction == 'R')
            player.x =  -1 * player.width/2;
    }

    @Override
    public void keyPressed (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (!checkDirectionCollision('U'))
                player.updateDirection('U');
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (!checkDirectionCollision('D'))
                player.updateDirection('D');
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (!checkDirectionCollision('R'))
                player.updateDirection('R');
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (!checkDirectionCollision ('L'))
                player.updateDirection('L');
        }

        if (player.direction == 'U')
            player.img = images.pacmanUpImage;
        else if (player.direction == 'D')
            player.img = images.pacmanDownImage;
        else if (player.direction == 'R')
            player.img = images.pacmanRightImage;
        else if (player.direction == 'L')
            player.img = images.pacmanLeftImage;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        move();
        repaint();
        
        if (e.getSource() == startButton) {
            startButton.setVisible(false);
            exitButton.setVisible(false);
        }
        else if (e.getSource() == exitButton)
            System.exit(0);
    }

    @Override
    public void keyReleased (KeyEvent e) {}

    @Override
    public void keyTyped (KeyEvent e) {}
}