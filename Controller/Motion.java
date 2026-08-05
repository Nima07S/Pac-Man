package Controller;

import Model.*;

import java.util.ArrayList;

public class Motion {
    private GameManager gameManager;
    private Pacman player;
    private ArrayList<Ghost> ghosts;
    private ArrayList<Block> walls;
    private ArrayList<Block> pellets;
    private ScoreManager info;

    public Motion (Pacman p, ArrayList<Ghost> gs, ArrayList<Block> ws, ArrayList<Block> ps, GameManager gm, ScoreManager sm) {
        this.player = p;
        this.ghosts = gs;
        this.walls = ws;
        this.pellets = ps;
        this.gameManager = gm;
        this.info = sm;
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
                    break;
                }
            }
        }
    }
}