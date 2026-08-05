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
    MyTextField usernameField;

    String username = "";

    MyImage images = new MyImage();

    ScoreManager info = new ScoreManager(3);

    ArrayList<Block> walls = new ArrayList<>();
    ArrayList<Block> pellets = new ArrayList<>();
    ArrayList<Ghost> ghosts = new ArrayList<>();

    Pacman player = new Pacman(SIZE*9, SIZE*16, 32, 32, null, info);

    Map map = new Map(images, pellets, walls, ghosts);

    GameManager gameManager = new GameManager(info);

    Timer loop;

    Database db;
    int highScore = 0;
    String highScoreName = "";

    Keyboard keyboard;

    Motion motion;

    public MyPanel (Database database) {
        db = database;
        highScore = db.highScore();
        highScoreName = db.highScoreName();

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        
        setLayout(null);
       
        startButton = new MyButton("START", 16, 130, 475, 120, 70, this);
        exitButton = new MyButton("EXIT", 16, 358, 475, 120, 70, this);

        pacmanLabel = new MyLabel ("PacMan", 70, Color.YELLOW, 50, 50, 500, 300);
        winLabel = new MyLabel("You Won!", 40, Color.WHITE, 100, 300, 400, 100);
        winLabel.setVisible(false);

        usernameField = new MyTextField("Enter your name", 128, 330, 352, 70);
        usernameField.setVisible(true);

        add(startButton);
        add(exitButton);
        add(pacmanLabel);
        add(winLabel);
        add(usernameField);
        
        setFocusable(true);
        keyboard = new Keyboard(player, walls, images, info);
        addKeyListener(keyboard);

        images.loadWall();
        images.loadPacman();
        images.loadGhosts();

        player.img = images.pacmanRightImage;
        
        map.loadMaze();

        motion = new Motion(player, ghosts, walls, pellets, gameManager, info);
                    
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
        for (Block w: walls)
            g.drawImage(w.img, w.x, w.y, w.width, w.height, null);

        g.drawImage(player.img, player.x, player.y, player.width, player.height, null);
        
        g.setColor(Color.WHITE);
        for (Block p: pellets)
            g.fillRect(p.x, p.y, p.width, p.height);


        for (Block G: ghosts) 
            g.drawImage(G.img, G.x, G.y, G.width, G.height, null);

        g.setFont(new Font("Emulogic", Font.PLAIN, 12));
        g.drawString("Score:" + info.getScore(), 5, 24);
        g.drawString("Lives:" + info.getLives(), 140, 24);
        g.drawString("High Score: " + highScoreName + " " + highScore, 280, 24);
        
        if (!gameManager.isGameStarted) {
            g.setColor(new Color(0, 0, 0, 170));
            g.fillRect(0, 0, width, height);
            MyButton.showButtons();
            pacmanLabel.setVisible(true);
            if (gameManager.playerWon)
                winLabel.setVisible(true);
        } 
    }

    public void update () {
        if (!gameManager.isGameStarted)
            return;

        motion.move();
        if (gameManager.checkGameOver(info)) {
            db.addRecord(username, info.getScore());
            highScore = db.highScore();
            highScoreName = db.highScoreName();
            MyButton.showButtons();
            pacmanLabel.setVisible(true);
        }        
        if (gameManager.checkWinning(pellets)) {
            db.addRecord(username, info.getScore());
            highScore = db.highScore();
            highScoreName = db.highScoreName();
        }
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        update();
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