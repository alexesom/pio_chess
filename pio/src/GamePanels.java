import javax.swing.*;
import java.awt.*;

public class GamePanels {
    public JPanel mainPanel = new JPanel();
    public JPanel titlePanel = new JPanel();
    public JPanel coordinatesPanel = new JPanel();
    public JPanel picPanel = new GameStartImage();
    public JPanel isAblePanel = new JPanel();
    public JPanel player1Panel = new JPanel();
    public JPanel player2Panel = new JPanel();
    public JPanel boardCoordinatesRightPanel = new JPanel();
    public JPanel boardCoordinatesBottomPanel = new JPanel();
    public JPanel capturedPiecesPanel1 = new JPanel();
    public JPanel capturedPiecesPanel2 = new JPanel();

    public GamePanels() {
        mainGamePanel();
        titleGamePanel();
        coordinatesGamePanel();
        picturePanel();
        isAbleToMovePanel();
        setPlayerPanels();
        setBoardCoordinatesPanels();
    }

    private void mainGamePanel() {
        mainPanel.setBackground(java.awt.Color.PINK);
    }

    private void picturePanel() {
        picPanel.setLayout(null);
    }

    private void titleGamePanel() {
        titlePanel.setBounds(150,50,560,60);
        titlePanel.setBackground(java.awt.Color.DARK_GRAY);
    }

    private void coordinatesGamePanel() {
        coordinatesPanel.setBounds(0,0,200,30);
        coordinatesPanel.setMaximumSize(new Dimension(200, 30));
        coordinatesPanel.setBackground(java.awt.Color.CYAN);
    }

    private void isAbleToMovePanel() {
        isAblePanel.setBounds(0,50,400,30);
        isAblePanel.setBackground(Color.orange);
    }

    private void setPlayerPanels() {
        player1Panel.setBounds(25,220,150,30);
        player1Panel.setMaximumSize(new Dimension(150, 30));
        player1Panel.setBackground(Color.red);

        capturedPiecesPanel1.setBounds(15,270,170,50);
        capturedPiecesPanel1.setMaximumSize(new Dimension(170,50));
        capturedPiecesPanel1.setBackground(Color.gray);

        player2Panel.setBounds(25,680,150,30);
        player2Panel.setMaximumSize(new Dimension(150, 30));
        player2Panel.setBackground(Color.red);

        capturedPiecesPanel2.setBounds(15,610,170,50);
        capturedPiecesPanel2.setMaximumSize(new Dimension(170,50));
        capturedPiecesPanel2.setBackground(Color.gray);
    }

    private void setBoardCoordinatesPanels() {
        boardCoordinatesBottomPanel.setBounds(200,740,560,30);
        boardCoordinatesBottomPanel.setMaximumSize(new Dimension(560,30));
        boardCoordinatesBottomPanel.setBackground(Color.gray);

        boardCoordinatesRightPanel.setBounds(760,180,30,590);
        boardCoordinatesRightPanel.setMaximumSize(new Dimension(30,590));
        boardCoordinatesRightPanel.setBackground(Color.gray);
    }
}