public class ScoreManager {
    private int lives;
    private int score = 0;

    public ScoreManager (int score, int lives) {
        this.lives = lives;
        this.score = score;
    }
    public int getScore () {
        return score + Pacman.eatenPelletCount * 10;
    }

    public void setScore (int score) {
        this.score = score;
    }

    public int getLives () {
        return lives;
    }

    public void setLives (int lives) {
        this.lives = lives;
    }

    public void resetInfo () {
        lives = 1;
        score = 0;
        Pacman.eatenPelletCount = 0;
    }

    public void liveDecrement () {
        lives--;
    }

    public void scoreDecrement () {
        score--;
    }
}