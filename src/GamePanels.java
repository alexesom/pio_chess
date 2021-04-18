import javax.swing.*;
import java.awt.*;

public class GamePanels {
    public JPanel mainPanel = new JPanel();
    public JPanel titlePanel = new JPanel();
    public JPanel picPanel = new GameStartImage();

    public GamePanels() {
        mainGamePanel();
        titleGamePanel();
        picturePanel();
    }

    private void mainGamePanel() {
        mainPanel.setBackground(java.awt.Color.PINK);
    }

    private void picturePanel() {
        picPanel.setLayout(null);
    }

    private void titleGamePanel() {
        titlePanel.setBounds(100,50,560,60);
        titlePanel.setBackground(java.awt.Color.DARK_GRAY);
    }
}