import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class GameInterface implements ActionListener {
    private JFrame frame = new JFrame("Chess");
    private JLabel titleText = new JLabel("CHESS GAME");
    private JButton startButton = new JButton("Start");
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
        frame.setSize(850,850);
        frame.setResizable(false);
    }

    private void createGamePanels() {
        chessboard.createChessboardPanel();
        frame.add(chessboard.chessboardPanel);
        frame.add(gamePanels.coordinatesPanel);
        frame.add(gamePanels.isAblePanel);
        frame.add(gamePanels.player1Panel);
        frame.add(gamePanels.player2Panel);
        frame.add(gamePanels.capturedPiecesPanel1);
        frame.add(gamePanels.capturedPiecesPanel2);
        frame.add(gamePanels.boardCoordinatesBottomPanel);
        frame.add(gamePanels.boardCoordinatesRightPanel);
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
        startButton.setBounds(375,150,100,30);
        gamePanels.picPanel.add(startButton);
    }

    private void createStartInterface() {
        createGameFrame();
        createStartButton();
        createGameTitle();

        frame.add(player1.createUserPanel(50,500,200,90));
        player1.setPlayerNumber(1);
        frame.add(player2.createUserPanel(600,500,200,90));
        player2.setPlayerNumber(2);

        frame.add(gamePanels.picPanel);

        frame.setVisible(true);
    }

    private void createGameInterface() {
        createGamePanels();
        bottomCoordinates.setFont(new Font("Arial", Font.ITALIC, 19));
        bottomCoordinates.setText("A           B           C           D            E           F           G           H");
        rightCoordinates.setFont(new Font("Arial", Font.ITALIC, 19));
        rightCoordinates.setText("<html><br>8<br><br><br>7<br><br><br>6<br><br><br>5<br><br><br>4<br><br><br>3<br><br><br>2<br><br><br>1</html>");
        gamePanels.coordinatesPanel.add(chessboard.textDisplay);
        gamePanels.isAblePanel.add(chessboard.isAble);
        name1.setText("player 1: " + player1.name );
        name2.setText("player 2: " + player2.name );

        gamePanels.player1Panel.add(name1);
        gamePanels.player2Panel.add(name2);
        gamePanels.boardCoordinatesBottomPanel.add(bottomCoordinates);
        gamePanels.boardCoordinatesRightPanel.add(rightCoordinates);

        gamePanels.capturedPiecesPanel1.add(chessboard.takenPieces1);
        gamePanels.capturedPiecesPanel2.add(chessboard.takenPieces2);
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