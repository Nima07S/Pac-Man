package Model;

public class ScoreManager {
    private int lives;
    private static int score = 0;

    public ScoreManager (int lives) {
        this.lives = lives;
    }
    public static int getScore () {
        return score;
    }

    public static void setScore (int s) {
        ScoreManager.score = s;
    }

    public int getLives () {
        return lives;
    }

    public void setLives (int lives) {
        this.lives = lives;
    }

    public void resetInfo () {
        lives = 5;
        setScore(0);
    }

    public void liveDecrement () {
        lives--;
    }

    public void scoreDecrement () {
        score--;
    }

    public static void addScore (int s) {
        score += s;
    }
}