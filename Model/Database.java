package Model;

import java.sql.*;

public class Database {
    private String url = "jdbc:sqlite:records.db";
    private Connection conn;

    public void connect () {
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Successfully Connected!");
        }
        catch (SQLException e) {
            System.out.println("Error connecting: " + e.getMessage());
        }
    }

    public void createTable () {
        String sql = """
                CREATE TABLE IF NOT EXISTS records (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL,
                score INTEGER NOT NULL);
                """;

        try {
            Statement state = conn.createStatement();
            state.execute(sql);
            System.out.println("Table Created!");
        }
        catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public void addRecord (String username, int score) {
        String sql = "INSERT INTO records (username, score) VALUES (?, ?)";

        try {
            PreparedStatement preState = conn.prepareStatement(sql);
            preState.setString(1, username);
            preState.setInt(2, score);
            preState.executeUpdate();
            System.out.println("Successfully Added Record!");
        }
        catch (SQLException e) {
            System.out.println("Error in adding record: " + e.getMessage());
        }
    }

    public int highScore () {
        String sql = "SELECT MAX(score) AS highScore FROM records";
        int highScore = 0;

        try {
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery(sql);

            if (result.next())
                highScore = result.getInt("highScore");
        }
        catch (SQLException e) {
            System.out.println("Error finding high score: " + e.getMessage());
        }

        return highScore;
    }

    public void disconnect () {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Successfully Disconnected!");
            }
        }
        catch (SQLException e) {
            System.out.println("Error disconnecting: " + e.getMessage());
        }
    }

    public String highScoreName () {
        String sql = "SELECT username FROM records ORDER BY score DESC LIMIT 1";
        String name = "Unknown";

        try {
            PreparedStatement preState = conn.prepareStatement(sql);
            ResultSet result = preState.executeQuery();

            if (result.next())
                name = result.getString("username");
        }
        catch (SQLException e) {
            System.out.println("Error finding name: " + e.getMessage());
        }
        
        return name;
    }
}