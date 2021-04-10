package chessPIO;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chessboard implements ActionListener {
    private JFrame frame = new JFrame("Chess");
    private JPanel mainPanel = new JPanel();
    private JPanel chessboardPanel = new JPanel();
    private JPanel textPanel = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JTextArea textDisplay = new JTextArea();
    private JLabel titleText = new JLabel("CHESS GAME");
    public Square[][] board = new Square[8][8];

    public Chessboard() {
        createFrame();
        createPanels();
        createChessboardSquares();
        displayBoard();
    }

    private void createFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setMinimumSize(new Dimension(800,800));
        frame.setResizable(false);
    }

    private void createPanels() {
        mainPanel.setBackground(Color.PINK);

        chessboardPanel.setLayout(new GridLayout(8, 8));
        chessboardPanel.setBounds(200,180,560,560);

        textPanel.setBounds(0,0,200,30);
        textPanel.setBackground(Color.CYAN);

        titlePanel.setBounds(100,50,560,60);
        titlePanel.setBackground(Color.DARK_GRAY);
    }

    private void createTitle() {
        titleText.setFont(titleText.getFont().deriveFont(32.0F));
        titleText.setForeground(Color.lightGray);
        titlePanel.add(titleText);
        frame.add(titlePanel);
    }

    private void createChessboardSquares() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square singleSquare = new Square();

                squareColor(i, j, singleSquare);
                squareCoordinates(i, j, singleSquare);

                singleSquare.square.addActionListener(this);

                board[i][j] = singleSquare;
                chessboardPanel.add(board[i][j].square);
            }
        }
    }

    private void squareColor(int i, int j, Square singleSquare) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
            singleSquare.setColor("black");
        else singleSquare.setColor("white");
    }

    private void squareCoordinates(int i, int j, Square singleSquare) {
        singleSquare.setXSquareCoordinate(j);
        singleSquare.setYSquareCoordinate(i);
    }

    public void displayText(int x, int y) {
        textDisplay.setText("coordinates: x: " + x + " y: " + y );
    }

    private void displayBoard() {
        frame.add(chessboardPanel);
        textPanel.add(textDisplay);
        frame.add(textPanel);
        createTitle();
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (source == board[i][j].square) {
                    int x = board[i][j].getXSquareCoordinate() + 1;
                    int y = board[i][j].getYSquareCoordinate() + 1;
                    System.out.println("nasze: " + x + ", " + y);
                    System.out.println("kolor: " + board[i][j].getColor());
                    displayText(j + 1, i + 1);
                }
            }
        }
    }
}

