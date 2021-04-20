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
    public JPanel capturedPiecesPanel1 = new JPanel();
    public JPanel capturedPiecesPanel2 = new JPanel();
    public JPanel endGamePanel = new JPanel();

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
        titlePanel.setBounds(270,30,560,100);
        titlePanel.setOpaque(false);
    }

    public void endGamePanel(UsernameBox player) {
        endGamePanel.setVisible(true);
        endGamePanel.add(new JLabel(player.name + " " + "wins! Congrats!"));
    }

    private void setPlayerPanels() {
        player1Panel.setBounds(755,50,200,50);
        player1Panel.setOpaque(false);

        capturedPiecesPanel1.setBounds(675,95,360,200);
        capturedPiecesPanel1.setBackground(Color.gray);

        player2Panel.setBounds(755,580,200,50);
        player2Panel.setOpaque(false);

        capturedPiecesPanel2.setBounds(675,375,360,200);
        capturedPiecesPanel2.setBackground(Color.gray);

        endGamePanel.setBounds(500,640, 200, 50);
        endGamePanel.setBackground(Color.lightGray);
        endGamePanel.setVisible(false);
    }

    private void setBoardCoordinatesPanels() {
        boardCoordinatesBottomPanel.setBounds(70,600,560,30);
        boardCoordinatesBottomPanel.setBackground(new Color(49, 46, 43));

        boardCoordinatesRightPanel.setBounds(40,40,30,590);
        boardCoordinatesRightPanel.setBackground(new Color(49, 46, 43));
    }
}