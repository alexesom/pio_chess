import Pieces.ChessColor;
import Pieces.Piece;
import Pieces.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Chessboard {
    public JPanel chessboardPanel = new JPanel();
    public JLayeredPane layer = new JLayeredPane();
    public Piece figure1 = new Piece(10, 10, Color.green);
    public Piece figure2 = new Piece(220, 220, Color.BLUE);
    public Square[][] board = new Square[8][8];
    private Adapter plsWork = new Adapter(layer);
    public JTextArea takenPieces1 = new JTextArea();
    public JTextArea takenPieces2 = new JTextArea();
    public static List<Piece> pieceList = null;

    public Chessboard() {
        createChessboardSquares();
        addFigure(figure1);
        //addFigure(figure2);
        addMouse();
    }

    public Chessboard(List<Piece> list) {
        pieceList = list;
        createChessboardSquares();
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

   /* public static Piece findPiece(Square initialSquare) {
        if (pieceList == null)
            return null;

        for(Piece item : pieceList) {
            if(item.getButtonSquare().equalsCoordinates(initialSquare))
                return item;
        }

        return null;
    }*/
}