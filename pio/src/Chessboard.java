package chessPIO;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chessboard implements ActionListener {
    public JPanel chessboardPanel = new JPanel();
    public Square[][] board = new Square[8][8];
    public JTextArea textDisplayer = new JTextArea();

    public Chessboard() {
        createChessboardSquares();
    }

    public void createChessboardPanel() {
        chessboardPanel.setLayout(new GridLayout(8, 8));
        chessboardPanel.setBounds(200,180,560,560);
    }

    private void createChessboardSquares() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Square singleSquare = new Square();

                squareColor(i, j, singleSquare);
                setChosenCoordinates(i, j, singleSquare);

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

    private void setChosenCoordinates(int i, int j, Square singleSquare) {
        singleSquare.setXSquareCoordinate(j);
        singleSquare.setYSquareCoordinate(i);
    }

    public void displayChosenCoordinates(int x, int y) {
        textDisplayer.setText("coordinates: x: " + x + " y: " + y );
    }

    public void actionPerformed(ActionEvent e) {
        Object chosenSquare = e.getSource();
        try {
            for (int i = 7; i >= 0; i--) {
                for (int j = 0; j < 8; j++) {
                    if (chosenSquare == board[i][j].square) {
                        int x = board[i][j].getXSquareCoordinate() + 1;
                        int y = board[i][j].getYSquareCoordinate() + 1;
                        displayChosenCoordinates(x, y);
                    }
                }
            }
        } catch (IllegalArgumentException sourceError) {
            System.err.println("ActionEvent fail");
        }
    }
}