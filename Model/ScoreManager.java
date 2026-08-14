package Model;

public class ScoreManager {
    private int lives;
    private int copyLive;
    private int score;

    public ScoreManager (int lives) {
        this.lives = lives;
        copyLive = lives;
        lives = 0;
    }
    public int getScore () {
        return score;
    }

    public void setScore (int s) {
        score = s;
    }

    public int getLives () {
        return lives;
    }

    public void setLives (int lives) {
        this.lives = lives;
    }

    public void resetInfo () {
        lives = copyLive;
        setScore(0);
    }

    public void liveDecrement () {
        lives--;
    }

    public void scoreDecrement () {
        score--;
    }

    public void addScore (int s) {
        score += s;
    }
}