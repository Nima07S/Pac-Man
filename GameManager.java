import java.util.ArrayList;

public class GameManager {
    public boolean isGameStarted = false;
    public boolean eatenAllPellets = false;
    public boolean playerWon = false;
    public boolean firstTime = true;

    public void checkWinnig (ArrayList<Block> pellets) {
        if (pellets.isEmpty()) {
            ScoreManager.addScore(500);
            isGameStarted = false;
            eatenAllPellets = true;
            playerWon = true;
        }
    }
}