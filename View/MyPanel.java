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
    MyLabel loseLabel;
    MyTextField usernameField;

    String username = "";

    MyImage images = new MyImage();

    ScoreManager info = new ScoreManager(1);

    Sound music = new Sound();

    ArrayList<Block> walls = new ArrayList<>();
    ArrayList<Block> pellets = new ArrayList<>();
    ArrayList<Ghost> ghosts = new ArrayList<>();

    Pacman player = new Pacman(SIZE*9, SIZE*16, 32, 32, null, info, music);

    Map map = new Map(images, pellets, walls, ghosts);

    Game game = new Game(info, music);

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
       
        startButton = new MyButton("START", 16, 130, 525, 120, 70, this);
        exitButton = new MyButton("EXIT", 16, 358, 525, 120, 70, this);

        pacmanLabel = new MyLabel ("PacMan", 70, Color.YELLOW, 50, 50, 500, 300);
        winLabel = new MyLabel("You Won!", 40, Color.WHITE, 100, 300, 400, 100);
        winLabel.setVisible(false);
        loseLabel = new MyLabel("GameOver", 40, Color.WHITE, 100, 300, 400, 100);
        loseLabel.setVisible(false);

        usernameField = new MyTextField("Enter your name", 128, 420, 352, 60);
        usernameField.setVisible(true);

        add(startButton);
        add(exitButton);
        add(pacmanLabel);
        add(winLabel);
        add(loseLabel);
        add(usernameField);
        
        setFocusable(true);
        keyboard = new Keyboard(player, walls, images, info);
        addKeyListener(keyboard);

        images.loadWall();
        images.loadPacman();
        images.loadGhosts();

        player.setImage(images.getRightPacmanImage());
        
        map.loadMaze();

        motion = new Motion(player, ghosts, walls, pellets, game, info);
                    
        // 1000 ms / 40 = 25 FPS
        loop = new Timer(40, this);
        loop.start();

        music.playBeginningMusic();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g) {
        for (Block w: walls)
            g.drawImage(w.getImage(), w.getX(), w.getY(), w.getWidth(), w.getHeight(), null);

        g.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
        
        g.setColor(Color.WHITE);
        for (Block p: pellets)
            g.fillRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());


        for (Block G: ghosts) 
            g.drawImage(G.getImage(), G.getX(), G.getY(), G.getWidth(), G.getHeight(), null);

        g.setFont(new Font("Emulogic", Font.PLAIN, 12));
        g.drawString("Score:" + info.getScore(), 5, 24);
        g.drawString("Lives:" + info.getLives(), 150, 24);
        g.drawString("High Score: " + highScoreName + " " + highScore, 280, 24);
        
        if (!game.isGameStarted) {
            g.setColor(new Color(0, 0, 0, 170));
            g.fillRect(0, 0, width, height);
            MyButton.showButtons();
            pacmanLabel.setVisible(true);
            if (game.playerWon)
                winLabel.setVisible(true);
            else if (!game.firstTime)
                loseLabel.setVisible(true);
        }
    }

    public void update () {
        if (!game.isGameStarted)
            return;

        motion.move();
        if (game.checkGameOver(info)) {
            db.addRecord(username, info.getScore());
            highScore = db.highScore();
            highScoreName = db.highScoreName();
            MyButton.showButtons();
            pacmanLabel.setVisible(true);
            usernameField.setText(username);
            usernameField.setVisible(true);
        }        
        if (game.checkWinning(pellets)) {
            db.addRecord(username, info.getScore());
            highScore = db.highScore();
            highScoreName = db.highScoreName();
            usernameField.setText(username);
            usernameField.setVisible(true);
        }
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        update();
        repaint();
         
        if (e.getSource() == startButton) {
            username = usernameField.getText();
            usernameField.setVisible(false);
            game.firstTime = false;
            info.resetInfo();
            game.isGameStarted = true;
            for (Ghost g: ghosts)
                g.resetPosition();
            player.resetPosition();
            pellets.clear();
            map.loadPellets();
            MyButton.hideButtons();
            MyLabel.hideLabels(pacmanLabel, winLabel, loseLabel);
            game.eatenAllPellets = false;
            if (info.getLives() == 0 || pellets.isEmpty()) {
                map.loadPellets();
                game.eatenAllPellets = false;
            }
            music.playBackMusic();
        }
        else if (e.getSource() == exitButton) {
            db.disconnect();
            System.exit(0);
        }
    }
}