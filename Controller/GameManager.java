package Controller;

import Model.Block;
import Model.ScoreManager;
import Model.Sound;

import java.util.ArrayList;

public class GameManager {
    public boolean isGameStarted = false;
    public boolean eatenAllPellets = false;
    public boolean playerWon = false;
    public boolean firstTime = true;
    private ScoreManager scoreManager;
    private Sound sound;

    public GameManager (ScoreManager sm, Sound s) {
        this.scoreManager = sm;
        this.sound = s;
    }

    public boolean checkWinning (ArrayList<Block> pellets) {
        if (pellets.isEmpty()) {
            scoreManager.addScore(500);
            isGameStarted = false;
            eatenAllPellets = true;
            playerWon = true;
            return true;
        }
        return false;
    }

    public boolean checkGameOver (ScoreManager info) {
        if (info.getLives() == 0) {
            isGameStarted = false;
            playerWon = false;
            eatenAllPellets = false;
            sound.playLoseMusic();
            return true;
        }
        return false;
    }
}