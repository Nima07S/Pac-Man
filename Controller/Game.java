package Controller;

import View.MyFrame;
import View.MyPanel;
import Model.Database;

public class Game {
    public static void main (String[] args) {
        Database database = new Database();
        database.connect();
        database.createTable();

        MyFrame frame = new MyFrame();
        MyPanel panel = new MyPanel(database);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}