import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInterface implements ActionListener {
    private JFrame frame = new JFrame("Chess");
    private JLabel titleText = new JLabel("CHESS GAME");
    private JButton startButton = new JButton("Start");
    public Chessboard chessboard = new Chessboard();
    private GamePanels gamePanels = new GamePanels();

    public GameInterface() {
        createStartInterface();
    }

    private void createGameFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setResizable(false);
    }

    private void createGamePanels() {
        chessboard.createChessboardPanel();
        frame.add(chessboard.chessboardPanel);
        frame.add(gamePanels.coordinatesPanel);
        frame.add(gamePanels.mainPanel);
    }

    private void createGameTitle() {
        titleText.setFont(titleText.getFont().deriveFont(32.0F));
        titleText.setForeground(java.awt.Color.lightGray);

        gamePanels.titlePanel.add(titleText);
        frame.add(gamePanels.titlePanel);
    }

    private void createStartButton() {
        startButton.setBackground(java.awt.Color.green);
        startButton.addActionListener(this);
        startButton.setBounds(325,150,100,30);
        gamePanels.picPanel.add(startButton);
    }

    private void createStartInterface() {
        createGameFrame();
        createStartButton();
        createGameTitle();
        frame.add(gamePanels.picPanel);
        frame.setVisible(true);
    }

    private void createGameInterface() {
        createGamePanels();
        gamePanels.coordinatesPanel.add(chessboard.textDisplay);
    }

    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        try {
            if (button == startButton){
                gamePanels.titlePanel.setVisible(false);
                gamePanels.picPanel.setVisible(false);
                createGameInterface();
            }
        } catch (IllegalArgumentException sourceError) {
            System.err.println("ActionEvent fail");
        }
    }
}