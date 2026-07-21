public class Game {
    public static void main (String[] args) {
        MyFrame frame = new MyFrame();
        MyPanel panel = new MyPanel();

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}