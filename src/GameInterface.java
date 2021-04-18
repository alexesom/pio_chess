import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInterface implements ActionListener {
    private JFrame frame = new JFrame("Chess");
    private JLabel titleText = new JLabel("CHESS GAME");
    private JButton startButton = new JButton("Start");
    public Chessboard chessboard = new Chessboard();
    private GamePanels gamePanels = new GamePanels();
    private UsernameBox player1 = new UsernameBox();
    private UsernameBox player2 = new UsernameBox();

    public GameInterface() {
        createStartInterface();
    }

    private void createGameFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,900);
        frame.setResizable(false);
    }

    private void createGamePanels() {
        chessboard.createChessboardPanel(200, 200, 560, 560);
        frame.add(chessboard.layer);
        frame.add(chessboard.chessboardPanel);
        frame.add(gamePanels.mainPanel);
    }

    private void createGameTitle() {
        titleText.setFont(titleText.getFont().deriveFont(32.0F));
        titleText.setForeground(Color.lightGray);

        gamePanels.titlePanel.add(titleText);
        frame.add(gamePanels.titlePanel);
    }

    private void createStartButton() {
        startButton.setBackground(Color.green);
        startButton.addActionListener(this);
        startButton.setBounds(325,150,100,30);
        gamePanels.picPanel.add(startButton);
    }

    private void createStartInterface() {
        createGameFrame();
        createStartButton();
        createGameTitle();

        frame.add(player1.createUserPanel(50,500,200,90));
        player1.setPlayerNumber(1);
        frame.add(player2.createUserPanel(550,500,200,90));
        player2.setPlayerNumber(2);

        frame.add(gamePanels.picPanel);

        frame.setVisible(true);
    }

    private void createGameInterface() {
        createGamePanels();
    }

    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        try {
            if (button == startButton){
                gamePanels.titlePanel.setVisible(false);
                gamePanels.picPanel.setVisible(false);
                player1.usernamePanel.setVisible(false);
                player2.usernamePanel.setVisible(false);
                createGameInterface();
            }
        } catch (IllegalArgumentException sourceError) {
            System.err.println("ActionEvent fail");
        }
    }
}