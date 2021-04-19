import Pieces.*;

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
        placeChessboardPieces();
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
        layer.add(figure.panel);
        board[x][y].setSquarePiece(figure);
    }

    private void addMouse(){
        layer.addMouseListener(plsWork);
    }

    private void placeChessboardPieces(){
        //Black pieces
        addFigure(new Rook(board[0][7], ChessColor.BLACK));
        addFigure(new Knight(board[1][7], ChessColor.BLACK));
        addFigure(new Bishop(board[2][7], ChessColor.BLACK));
        addFigure(new Queen(board[3][7], ChessColor.BLACK));
        addFigure(new King(board[4][7], ChessColor.BLACK));
        addFigure(new Bishop(board[5][7], ChessColor.BLACK));
        addFigure(new Knight(board[6][7], ChessColor.BLACK));
        addFigure(new Rook(board[7][7], ChessColor.BLACK));
        addFigure(new Pawn(board[0][6], ChessColor.BLACK));
        addFigure(new Pawn(board[1][6], ChessColor.BLACK));
        addFigure(new Pawn(board[2][6], ChessColor.BLACK));
        addFigure(new Pawn(board[3][6], ChessColor.BLACK));
        addFigure(new Pawn(board[4][6], ChessColor.BLACK));
        addFigure(new Pawn(board[5][6], ChessColor.BLACK));
        addFigure(new Pawn(board[6][6], ChessColor.BLACK));
        addFigure(new Pawn(board[7][6], ChessColor.BLACK));

        //White pieces
        addFigure(new Rook(board[0][0], ChessColor.WHITE));
        addFigure(new Knight(board[1][0], ChessColor.WHITE));
        addFigure(new Bishop(board[2][0], ChessColor.WHITE));
        addFigure(new Queen(board[3][0], ChessColor.WHITE));
        addFigure(new King(board[4][0], ChessColor.WHITE));
        addFigure(new Bishop(board[5][0], ChessColor.WHITE));
        addFigure(new Knight(board[6][0], ChessColor.WHITE));
        addFigure(new Rook(board[7][0], ChessColor.WHITE));
        addFigure(new Pawn(board[0][1], ChessColor.WHITE));
        addFigure(new Pawn(board[1][1], ChessColor.WHITE));
        addFigure(new Pawn(board[2][1], ChessColor.WHITE));
        addFigure(new Pawn(board[3][1], ChessColor.WHITE));
        addFigure(new Pawn(board[4][1], ChessColor.WHITE));
        addFigure(new Pawn(board[5][1], ChessColor.WHITE));
        addFigure(new Pawn(board[6][1], ChessColor.WHITE));
        addFigure(new Pawn(board[7][1], ChessColor.WHITE));
    }
}