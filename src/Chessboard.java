import Pieces.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class Chessboard {
    public JPanel chessboardPanel = new JPanel();
    public JLayeredPane layer = new JLayeredPane();
    public Piece figure1 = new Piece(10, 10, Color.green);
    public Piece figure2 = new Piece(220, 220, Color.BLUE);
    public Square[][] board = new Square[8][8];
    private AdapterSami plsWork = new AdapterSami(layer);

    public Chessboard() {
        createChessboardSquares();
        addFigure(figure1);
        addFigure(figure2);
        addMouse();
    }

    public void createChessboardPanel(int xGap, int yGap, int width, int height) {
        chessboardPanel.setLayout(null);
        chessboardPanel.setBounds(xGap, yGap, width, height);
        layer.setBounds(xGap, yGap, width, height);
    }

    private void createChessboardSquares() {
        for (int iy = 7; iy >= 0; iy--) {
            for (int jx = 0; jx < 8; jx++) {
                board[jx][iy] = new Square(jx, iy,  560, 560);
                board[jx][iy].setSquareColor(jx, iy);
                Color boardSquareColor = board[jx][iy].getSquareColor();
                board[jx][iy].squarePanel.setBackground(boardSquareColor);
                chessboardPanel.add(board[jx][iy].squarePanel);
                System.out.println(board[jx][iy].squarePanel.getLocation() + " " + iy + " " + jx);
            }
        }
    }

    private void addFigure(Piece figure) {
        layer.add(figure.label1);

    }
    private void addMouse(){
        layer.addMouseListener(plsWork);
    }
}