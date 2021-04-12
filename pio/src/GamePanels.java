package chessPIO;

import javax.swing.*;
import java.awt.*;

public class GamePanels {
    public JPanel mainPanel = new JPanel();
    public JPanel titlePanel = new JPanel();
    public JPanel coordinatesPanel = new JPanel();
    public JPanel picPanel = new GameStartImage();

    public GamePanels() {
        mainGamePanel();
        titleGamePanel();
        coordinatesGamePanel();
        picturePanel();
    }

    private void mainGamePanel() {
        mainPanel.setBackground(Color.PINK);
    }

    private void picturePanel() {
        picPanel.setLayout(null);
    }

    private void titleGamePanel() {
        titlePanel.setBounds(100,50,560,60);
        titlePanel.setBackground(Color.DARK_GRAY);
    }

    private void coordinatesGamePanel() {
        coordinatesPanel.setBounds(0,0,200,30);
        coordinatesPanel.setMaximumSize(new Dimension(200, 30));
        coordinatesPanel.setBackground(Color.CYAN);
    }
}