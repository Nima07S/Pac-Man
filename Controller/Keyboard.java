package Controller;

import Model.Pacman;
import Model.Block;
import Model.ScoreManager;
import View.MyImage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Keyboard implements KeyListener {
    private Pacman player;
    private ArrayList<Block> walls;
    private MyImage images;
    
    public Keyboard (Pacman p, ArrayList<Block> w, MyImage imgs) {
        this.player = p;
        this.walls = w;
        this.images = imgs;
    }

    @Override
    public void keyPressed (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (!Collision.checkDirectionCollision('U', player, walls)) {
                player.updateDirection('U');
                ScoreManager.addScore(-1);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (!Collision.checkDirectionCollision('D', player, walls)) {
                player.updateDirection('D');
                ScoreManager.addScore(-1);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (!Collision.checkDirectionCollision('R', player, walls)) {
                player.updateDirection('R');
                ScoreManager.addScore(-1);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (!Collision.checkDirectionCollision('L', player, walls)) {
                player.updateDirection('L');
                ScoreManager.addScore(-1);
            }
        }

        images.setPacManImage(player);
    }

    @Override
    public void keyReleased (KeyEvent e) {}

    @Override
    public void keyTyped (KeyEvent e) {}
}