package Controller;

import Model.*;

import java.util.ArrayList;

public class Motion {
    private Game game;
    private Pacman player;
    private ArrayList<Ghost> ghosts;
    private ArrayList<Block> walls;
    private ArrayList<Block> pellets;
    private ScoreManager info;

    public Motion (Pacman p, ArrayList<Ghost> gs, ArrayList<Block> ws, ArrayList<Block> ps, Game gm, ScoreManager sm) {
        this.player = p;
        this.ghosts = gs;
        this.walls = ws;
        this.pellets = ps;
        this.game = gm;
        this.info = sm;
    }

    public void move () {
        if (game.isGameStarted) {
            player.actualMove(walls, game.isGameStarted);
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