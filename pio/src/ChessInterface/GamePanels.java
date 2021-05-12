package ChessInterface;

import javax.swing.*;
import java.awt.*;

public class GamePanels {
    public JPanel mainPanel = new JPanel();
    public JPanel titlePanel = new JPanel();
    public JPanel picPanel = new GameStartImage();
    public JPanel player1Panel = new JPanel();
    public JPanel player2Panel = new JPanel();
    public JPanel boardCoordinatesRightPanel = new JPanel();
    public JPanel boardCoordinatesBottomPanel = new JPanel();
    public static JPanel endGamePanel = new JPanel();

    public GamePanels() {
        mainGamePanel();
        titleGamePanel();
        picturePanel();
        setPlayerPanels();
        setBoardCoordinatesPanels();
    }

    private void mainGamePanel() {
        mainPanel.setBackground(Color.white);
    }

    private void picturePanel() {
        picPanel.setLayout(null);
    }

    private void titleGamePanel() {
        titlePanel.setBounds(420,30,560,100);
        titlePanel.setOpaque(false);
    }

    public static void endGamePanel(String player) {
        endGamePanel.setVisible(false);
        endGamePanel.setBounds(400,680,400,80);
        endGamePanel.setOpaque(false);
        JLabel label = new JLabel(player + " " + "wins! Congrats!");
        endGamePanel.add(label);
        label.setForeground(new Color(112, 206, 85));
        Font font = new Font("Verdana", Font.BOLD, 23);
        label.setFont(font);
    }

    private void setPlayerPanels() {
        player1Panel.setBounds(755,50,300,50);
        player1Panel.setOpaque(false);

        player2Panel.setBounds(755,580,300,50);
        player2Panel.setOpaque(false);
    }

    private void setBoardCoordinatesPanels() {
        boardCoordinatesBottomPanel.setBounds(70,600,560,30);
        boardCoordinatesBottomPanel.setBackground(new Color(49, 46, 43));

        boardCoordinatesRightPanel.setBounds(40,40,30,590);
        boardCoordinatesRightPanel.setBackground(new Color(49, 46, 43));
    }
}