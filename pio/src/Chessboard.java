import Pieces.ChessColor;
import Pieces.Piece;
import Pieces.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Chessboard implements ActionListener {
    public JPanel chessboardPanel = new JPanel();
    public Square[][] board = new Square[8][8];
    public JTextArea textDisplay = new JTextArea();
    public JTextArea isAble = new JTextArea();
    public JTextArea takenPieces1 = new JTextArea();
    public JTextArea takenPieces2 = new JTextArea();
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
            singleSquare.setColor(ChessColor.WHITE);
        else singleSquare.setColor(ChessColor.BLACK);
    }

    private void setChosenCoordinates(int iy, int jx, Square singleSquare) {
        singleSquare.setXSquareCoordinate(jx);
        singleSquare.setYSquareCoordinate(iy);
    }

    // tu mozna spradzic czy dziala wasza funkcja isAbleToMove dla wybranego pionka
    // isAble.setText("is able? " + pieceList.get(INDEX WYBRANEGO PIONKA Z LISTY).isAbleToMove(board[y - 1][x - 1]));
    public void displayChosenCoordinates(int x, int y) {
        textDisplay.setText("coordinates: x: " + x + " y: " + y );
        isAble.setText("is able? " + pieceList.get(14).isAbleToMove(board[y - 1][x - 1]));
        takenPieces1.setText("place for captured pieces 1");
        takenPieces2.setText("place for captured pieces 2");
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