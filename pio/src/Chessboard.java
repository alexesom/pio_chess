import Pieces.*;
import Pieces.Color;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class Chessboard implements ActionListener {
    public JPanel chessboardPanel = new JPanel();
    public Square[][] board = new Square[8][8];
    public JTextArea textDisplay = new JTextArea();
    public static List<Piece> pieceList = null;

    public Chessboard() {

        createChessboardSquares();
    }

    public Chessboard(List<Piece> list) {
        pieceList = list;
        createChessboardSquares();
    }

    public void createChessboardPanel() {
        chessboardPanel.setLayout(new GridLayout(8, 8));
        chessboardPanel.setBounds(200,180,560,560);
    }

    private void createChessboardSquares() {
        for (int iy = 7; iy >= 0; iy--) {
            for (int jx = 0; jx < 8; jx++) {
                Piece foundPiece = findPiece(new Square(jx, iy));
                Square singleSquare;
                if(foundPiece == null)
                    singleSquare = new Square();
                else singleSquare = foundPiece.getButtonSquare();



                squareColor(iy, jx, singleSquare);
                setChosenCoordinates(iy, jx, singleSquare);
                singleSquare.button.setMargin(new Insets(0,0,0,0));

                singleSquare.button.addActionListener(this);

                board[iy][jx] = singleSquare;
                chessboardPanel.add(board[iy][jx].button);
            }
        }
    }

    private void squareColor(int i, int j, Square singleSquare) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
            singleSquare.setColor(Color.WHITE);
        else singleSquare.setColor(Color.BLACK);
    }

    private void setChosenCoordinates(int iy, int jx, Square singleSquare) {
        singleSquare.setXSquareCoordinate(jx);
        singleSquare.setYSquareCoordinate(iy);
    }

    public void displayChosenCoordinates(int x, int y) {
        textDisplay.setText("coordinates: x: " + x + " y: " + y );
    }

    public void actionPerformed(ActionEvent e) {
        Object chosenSquare = e.getSource();
        try {
            for (int i = 7; i >= 0; i--) {
                for (int j = 0; j < 8; j++) {
                    if (chosenSquare == board[i][j].button) {
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

    public static Piece findPiece(Square initialSquare) {
        if (pieceList == null)
            return null;

        for(Piece item : pieceList) {
            if(item.getButtonSquare().equalsCoordinates(initialSquare))
                return item;
        }

        return null;
    }
}