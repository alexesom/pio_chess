package ChessInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class GameInterface implements ActionListener {
    private JFrame frame = new JFrame("Chess");
    private JLabel titleText = new JLabel("CHESS GAME");
    private JButton startButton = new JButton("START");
    public Chessboard chessboard = new Chessboard();
    private GamePanels gamePanels = new GamePanels();
    private UsernameBox player1 = new UsernameBox();
    private UsernameBox player2 = new UsernameBox();
    public JLabel name1 = new JLabel();
    public JLabel name2 = new JLabel();
    public JLabel bottomCoordinates = new JLabel();
    public JLabel rightCoordinates = new JLabel();

    public GameInterface() {
        createStartInterface();
    }

    private void createGameFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,800);
        frame.setResizable(false);
    }

    private void createGamePanels() {
        chessboard.createChessboardPanel(70, 40, 560, 560);
        frame.add(chessboard.layer);
        frame.add(chessboard.backlightPanel);
        frame.add(chessboard.chessboardPanel);
        frame.add(gamePanels.player1Panel);
        frame.add(gamePanels.player2Panel);
        frame.add(chessboard.capturedPiecesPanel1);
        frame.add(chessboard.capturedPiecesPanel2);
        frame.add(gamePanels.boardCoordinatesBottomPanel);
        frame.add(gamePanels.boardCoordinatesRightPanel);
        frame.add(gamePanels.mainPanel);
    }

    private void createGameTitle() {
        titleText.setFont(titleText.getFont().deriveFont(64.0F));
        titleText.setForeground(Color.lightGray);

        gamePanels.titlePanel.add(titleText);
        frame.add(gamePanels.titlePanel);
    }

    private void createStartButton() {
        startButton.setBackground(Color.green);
        startButton.addActionListener(this);
        startButton.setBounds(650,150,100,30);
        gamePanels.picPanel.add(startButton);
    }

    private void createStartInterface() {
        createGameFrame();
        createStartButton();
        createGameTitle();

        frame.add(player1.createUserPanel(100,550,200,90));
        player1.setPlayerNumber(1);
        frame.add(player2.createUserPanel(1080,550,200,90));
        player2.setPlayerNumber(2);

        frame.add(gamePanels.picPanel);

        frame.setVisible(true);
    }

    private void createGameInterface() {
        createGamePanels();
        bottomCoordinates.setFont(new Font("Arial", Font.ITALIC, 19));
        bottomCoordinates.setText("A           B           C           D            E           F           G           H");
        bottomCoordinates.setForeground(new Color(151,149,148));
        rightCoordinates.setFont(new Font("Arial", Font.ITALIC, 19));
        rightCoordinates.setText("<html><br>8<br><br><br>7<br><br><br>6<br><br><br>5<br><br><br>4<br><br><br>3<br><br><br>2<br><br><br>1</html>");
        rightCoordinates.setForeground(new Color(151,149,148));

        if(player1.name == null || player1.name.length() == 0) {
            player1.name = "Player 1";
        }
        name1.setFont(new Font("Arial", Font.BOLD, 23));
        name1.setText(player1.name);

        if(player2.name == null || player2.name.length() == 0) {
            player2.name = "Player 2";
        }
        name2.setFont(new Font("AvantGarde", Font.BOLD, 23));
        name2.setText(player2.name);

        gamePanels.player1Panel.add(name1);
        gamePanels.player2Panel.add(name2);
        gamePanels.boardCoordinatesBottomPanel.add(bottomCoordinates);
        gamePanels.boardCoordinatesRightPanel.add(rightCoordinates);

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