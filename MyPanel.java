import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class MyPanel extends JPanel implements ActionListener, KeyListener {
    final int SIZE = 32;
    int row = 22, column = 19;
    int width = SIZE*column;
    int height = SIZE*row;

    JButton startButton;
    JButton exitButton;
    JLabel pacmanLabel;
    JLabel winLabel;
    JTextField usernameField;

    String username = "";

    MyImage images = new MyImage();
    Map map = new Map();

    ArrayList<Block> walls = new ArrayList<>();
    ArrayList<Block> pellets = new ArrayList<>();
    ArrayList<Ghost> ghosts = new ArrayList<>();

    Pacman player = new Pacman(SIZE*9, SIZE*16, 32, 32, null);
    
    boolean isGameStarted = false;
    boolean eatenAllPellets = false;
    boolean playerWon = false;
    boolean firstTime = true;

    ScoreManager info = new ScoreManager(3);

    Timer loop;

    public MyPanel () {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        
        setLayout(null);
       
        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 22));
        startButton.setBounds(130, 450, 120, 80);
        startButton.addActionListener(this);

        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Arial", Font.BOLD, 22));
        exitButton.setBounds(358, 450, 120, 80);
        exitButton.addActionListener(this);

        pacmanLabel = new JLabel ("PacMan");
        pacmanLabel.setFont(new Font("Arial", Font.BOLD, 75));
        pacmanLabel.setBounds(150, 75, 400, 200);
        pacmanLabel.setForeground(Color.YELLOW);

        winLabel = new JLabel("You Won!");
        winLabel.setForeground(Color.WHITE);
        winLabel.setFont(new Font("Arial", Font.BOLD, 40));
        winLabel.setBounds(200, 200, 300, 100);
        winLabel.setVisible(false);

        usernameField = new JTextField("Enter your name");
        usernameField.setFont(new Font("Arial", Font.BOLD, 24));
        usernameField.setBounds(200, 300, 200, 70);
        usernameField.setVisible(true);


        add(startButton);
        add(exitButton);
        add(pacmanLabel);
        add(winLabel);
        add(usernameField);
        
        setFocusable(true);
        addKeyListener(this);

        images.loadWall();
        images.loadPacman();
        images.loadGhosts();

        player.img = images.pacmanRightImage;
        
        for (int i=0; i<map.getMaze().length; i++) {
            for (int j=0; j<map.getMaze()[i].length(); j++) {
                switch (map.getMaze()[i].charAt(j)) {
                    case 'x':
                        Block wall = new Block(32*j, 32*i, SIZE, SIZE, images.wallImage);
                        walls.add(wall);
                        break;
                    case ' ':
                        Block pellet = new Block(j*32+13, i*32+13, 6, 6, null);
                        pellets.add(pellet);
                        break;
                    case 'R':
                        Ghost redGhost = new Ghost(SIZE*9, SIZE*9, SIZE, SIZE, images.redGhostImage);
                        ghosts.add(redGhost);
                        break;
                    case 'G':
                        Ghost greenGhost = new Ghost(SIZE*8, SIZE*10, SIZE, SIZE, images.greenGhostImage);
                        ghosts.add(greenGhost);
                        break;
                    case 'O':
                        Ghost orangeGhost = new Ghost(SIZE*10, SIZE*10, SIZE, SIZE, images.orangeGhostImage);
                        ghosts.add(orangeGhost);
                        break;
                    case 'P':
                        Ghost pinkGhost = new Ghost(SIZE*9, SIZE*11, SIZE, SIZE, images.pinkGhostImage);
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

        g.setFont(new Font("Airal", Font.PLAIN, 20));
        g.drawString("Score: " + ScoreManager.getScore(), 8, 24);
        g.drawString("Lives: " + info.getLives(), 160, 24);
        g.drawString("High Score: " + username + " - " + ScoreManager.getScore(), 360, 24);
        
        if (!isGameStarted) {
            g.setColor(new Color(0, 0, 0, 170));
            g.fillRect(0, 0, width, height);
            startButton.setVisible(true);
            exitButton.setVisible(true);
            pacmanLabel.setVisible(true);
            if (playerWon)
                winLabel.setVisible(true);
        } 
    }

    private boolean checkDirectionCollision (char ch) {
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
        if (isGameStarted) {
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

            Ghost.ghostsMove(ghosts, walls);
            player.pacmanPelletCollision(player, pellets);

            for (Ghost ghost: ghosts) {
                if (Collision.checkCollision(player, ghost)) {
                    info.liveDecrement();
                    player.resetPosition();
                    for (Ghost g: ghosts)
                        g.resetPosition();
                    if (info.getLives() == 0) {
                        isGameStarted = false;
                        playerWon = false;
                        startButton.setVisible(true);
                        exitButton.setVisible(true);
                        pacmanLabel.setVisible(true);
                        info.resetInfo();
                        break;
                    }
                    break;
                }
            }

            if (pellets.isEmpty()) {
                ScoreManager.addScore(500);
                isGameStarted = false;
                eatenAllPellets = true;
                playerWon = true;
            }
        }  
    }

    @Override
    public void keyPressed (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (!checkDirectionCollision('U')) {
                player.updateDirection('U');
                ScoreManager.addScore(-1);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (!checkDirectionCollision('D')) {
                player.updateDirection('D');
                ScoreManager.addScore(-1);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (!checkDirectionCollision('R')) {
                player.updateDirection('R');
                ScoreManager.addScore(-1);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (!checkDirectionCollision('L')) {
                player.updateDirection('L');
                ScoreManager.addScore(-1);
            }
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
            if (firstTime) {
                username = usernameField.getText();
                usernameField.setVisible(false);
                firstTime = false;
            }
            info.resetInfo();
            isGameStarted = true;
            for (Ghost g: ghosts)
                g.resetPosition();
            player.resetPosition();
            pellets.clear();
            Pellet.loadPellets(pellets, map);
            startButton.setVisible(false);
            exitButton.setVisible(false);
            pacmanLabel.setVisible(false);
            winLabel.setVisible(false);
            eatenAllPellets = false;
            if (info.getLives() == 0 || pellets.isEmpty()) {
                Pellet.loadPellets(pellets, map);
                eatenAllPellets = false;
            }
        }
        else if (e.getSource() == exitButton)
            System.exit(0);
    }

    @Override
    public void keyReleased (KeyEvent e) {}

    @Override
    public void keyTyped (KeyEvent e) {}
}