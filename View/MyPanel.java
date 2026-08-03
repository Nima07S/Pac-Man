package View;

import Model.*;
import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class MyPanel extends JPanel implements ActionListener {
    final int SIZE = 32;
    int row = 22, column = 19;
    int width = SIZE*column;
    int height = SIZE*row;

    MyButton startButton;
    MyButton exitButton;
    MyLabel pacmanLabel;
    MyLabel winLabel;
    JTextField usernameField;

    String username = "";

    MyImage images = new MyImage();

    ArrayList<Block> walls = new ArrayList<>();
    ArrayList<Block> pellets = new ArrayList<>();
    ArrayList<Ghost> ghosts = new ArrayList<>();

    Pacman player = new Pacman(SIZE*9, SIZE*16, 32, 32, null);

    Map map = new Map(images, pellets, walls, ghosts);

    ScoreManager info = new ScoreManager(3);
    GameManager gameManager = new GameManager();

    Timer loop;

    Database db;
    int highScore = 0;
    String highScoreName = "";

    Keyboard keyboard;

    public MyPanel (Database database) {
        db = database;
        highScore = db.highScore();
        highScoreName = db.highScoreName();

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        
        setLayout(null);
       
        startButton = new MyButton("START", 22, 130, 450, 120, 80, this);
        exitButton = new MyButton("EXIT", 22, 358, 450, 120, 80, this);

        pacmanLabel = new MyLabel ("PacMan", 75, Color.YELLOW, 150, 75, 400, 200);
        winLabel = new MyLabel("You Won!", 40, Color.WHITE, 200, 200, 300, 100);
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
        keyboard = new Keyboard(player, walls, images);
        addKeyListener(keyboard);

        images.loadWall();
        images.loadPacman();
        images.loadGhosts();

        player.img = images.pacmanRightImage;
        
        map.loadMaze();
                    
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
        g.drawString("High Score: " + highScoreName + " " + highScore, 300, 24);
        
        if (!gameManager.isGameStarted) {
            g.setColor(new Color(0, 0, 0, 170));
            g.fillRect(0, 0, width, height);
            MyButton.showButtons();
            pacmanLabel.setVisible(true);
            if (gameManager.playerWon)
                winLabel.setVisible(true);
        } 
    }

    public void move () {
        if (gameManager.isGameStarted) {
            player.actualMove(walls, gameManager.isGameStarted);
            player.teleporting();
            Ghost.ghostsMove(ghosts, walls);
            player.pacmanPelletCollision(player, pellets);
        
            for (Ghost ghost: ghosts) {
                if (Collision.checkCollision(player, ghost)) {
                    info.liveDecrement();
                    player.resetPosition();
                    for (Ghost g: ghosts)
                        g.resetPosition();
                    if (gameManager.checkGameOver(info)) {
                        db.addRecord(username, ScoreManager.getScore());
                        highScore = db.highScore();
                        highScoreName = db.highScoreName();
                        MyButton.showButtons();
                        pacmanLabel.setVisible(true);
                    }
                    break;
                }
            }
            if (gameManager.checkWinning(pellets)) {
                db.addRecord(username, ScoreManager.getScore());
                highScore = db.highScore();
                highScoreName = db.highScoreName();
            }
        }
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        move();
        repaint();
         
        if (e.getSource() == startButton) {
            username = usernameField.getText();
            usernameField.setVisible(false);
            gameManager.firstTime = false;
            info.resetInfo();
            gameManager.isGameStarted = true;
            for (Ghost g: ghosts)
                g.resetPosition();
            player.resetPosition();
            pellets.clear();
            Pellet.loadPellets(pellets, map);
            MyButton.hideButtons();
            MyLabel.hideLabels(pacmanLabel, winLabel);
            gameManager.eatenAllPellets = false;
            if (info.getLives() == 0 || pellets.isEmpty()) {
                Pellet.loadPellets(pellets, map);
                gameManager.eatenAllPellets = false;
            }
        }
        else if (e.getSource() == exitButton) {
            db.disconnect();
            System.exit(0);
        }
    }
}