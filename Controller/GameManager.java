package Controller;

import Model.Block;
import Model.ScoreManager;

import java.util.ArrayList;

public class GameManager {
    public boolean isGameStarted = false;
    public boolean eatenAllPellets = false;
    public boolean playerWon = false;
    public boolean firstTime = true;
    private ScoreManager scoreManager;

    public GameManager (ScoreManager sm) {
        this.scoreManager = sm;
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
            return true;
        }
        return false;
    }
}