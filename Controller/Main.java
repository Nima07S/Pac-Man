package Controller;

import View.MyFrame;
import View.MyPanel;
import Model.Database;

import javax.swing.UIManager;

public class Main {
    public static void main (String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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