import Pieces.ChessColor;
import Pieces.Piece;
import Pieces.Square;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Chessboard {
    public JPanel chessboardPanel = new JPanel();
    public JLayeredPane layer = new JLayeredPane();
    public Piece figure1 = new Piece(1, 1, ChessColor.BLACK);
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
        int x = figure.getxPieceCoordinate();
        int y = figure.getyPieceCoordinate();
        layer.add(figure.getPiecePanel());
        board[x][y].setSquarePiece(figure);
    }

    private void addMouse(){
        layer.addMouseListener(plsWork);
    }
}