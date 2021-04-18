import Pieces.*;
import Pieces.ChessColor;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class Chessboard {
    public JPanel chessboardPanel = new JPanel();
    public Square[][] board = new Square[8][8];
    public JTextArea textDisplay = new JTextArea();
    public JTextArea isAble = new JTextArea();
    public static List<Piece> pieceList = null;

    public Chessboard() {
        createChessboardSquares();
    }


    public void createChessboardPanel(int xGap, int yGap, int width, int height) {
        chessboardPanel.setLayout(null);
        chessboardPanel.setBounds(xGap, yGap, width, height);

    }

    private void createChessboardSquares() {
        for (int iy = 7; iy >= 0; iy--) {
            for (int jx = 0; jx < 8; jx++) {
                board[jx][iy] = new Square(jx, iy, 200, 200, 560, 560);
                board[jx][iy].setSquareColor(jx, iy);
                Color boardSquareColor = board[jx][iy].getSquareColor();
                board[jx][iy].squarePanel.setBackground(boardSquareColor);
                chessboardPanel.add(board[jx][iy].squarePanel);
                System.out.println(board[jx][iy].squarePanel.getLocation() + " " + iy + " " + jx);
            }
        }
    }



    private void setChosenCoordinates(int iy, int jx, Square singleSquare) {
        singleSquare.setXSquareCoordinate(jx);
        singleSquare.setYSquareCoordinate(iy);
    }

    // tu mozna spradzic czy dziala wasza funkcja isAbleToMove dla wybranego pionka
    // isAble.setText("is able? " + pieceList.get(INDEX WYBRANEGO PIONKA Z LISTY).isAbleToMove(board[y - 1][x - 1]));
    public void displayChosenCoordinates(int x, int y) {
        textDisplay.setText("coordinates: x: " + x + " y: " + y );
        isAble.setText("is able? " + pieceList.get(2).isAbleToMove(board[y - 1][x - 1]));
    }

}