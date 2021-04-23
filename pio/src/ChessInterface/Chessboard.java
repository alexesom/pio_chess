package ChessInterface;

import Pieces.*;

import java.awt.Color;

import javax.swing.*;
import java.util.List;

public class Chessboard {
    public JPanel chessboardPanel = new JPanel();
    public JLayeredPane layer = new JLayeredPane();
    public static Square[][] board = new Square[8][8];
    public JLayeredPane capturedPiecesPanel1 = new JLayeredPane();
    public JLayeredPane capturedPiecesPanel2 = new JLayeredPane();
    public static List<Piece> pieceList = null;
    private Adapter mouseAdapter = new Adapter(layer, capturedPiecesPanel1, capturedPiecesPanel2);

    public Chessboard() {
        createChessboardSquares();
        createCapturedPiecesPanel();
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

    public void createCapturedPiecesPanel() {
        capturedPiecesPanel1.setBounds(675, 95, 560, 200);
        capturedPiecesPanel1.setOpaque(false);

        capturedPiecesPanel2.setBounds(675, 375, 560, 300);
        capturedPiecesPanel2.setOpaque(false);
    }

    private void createChessboardSquares() {
        for (int iy = 7; iy >= 0; iy--) {
            for (int jx = 0; jx < 8; jx++) {
                board[jx][iy] = new Square(jx, iy, 560, 560);
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

    private void addMouse() {
        layer.addMouseListener(mouseAdapter);
    }

    public static void movePieceInSquares(Square startSquare, Square destinationSquare) {
        int xPiece = destinationSquare.getXSquareCoordinate();
        int yPiece = destinationSquare.getYSquareCoordinate();
        destinationSquare.setSquarePiece(startSquare.getSquarePiece());
        destinationSquare.getSquarePiece().setxPieceCoordinate(xPiece);
        destinationSquare.getSquarePiece().setyPieceCoordinate(yPiece);
        startSquare.setSquarePiece(null);
    }

    /*
    Place the pieces in their starting positions
     */
    private void placeChessboardPieces() {
        // Black pieces
        placePieces(7, Color.BLACK);
        placePawns(8,6, Color.BLACK);

        // White pieces
        placePieces(0, Color.WHITE);
        placePawns(8,1, Color.WHITE);
    }

    /*
    places the standard piece set of a given color in the specified row
    (Rook, Knight, Bishop, Queen, King, Bishop, Knight and Rook in this order)
     */
    private void placePieces(int row, Color color) {
        addFigure(new Rook(board[0][row], color));
        addFigure(new Knight(board[1][row], color));
        addFigure(new Bishop(board[2][row], color));
        addFigure(new Queen(board[3][row], color));
        addFigure(new King(board[4][row], color));
        addFigure(new Bishop(board[5][row], color));
        addFigure(new Knight(board[6][row], color));
        addFigure(new Rook(board[7][row], color));
    }

    /*
    places Pawns of a given color in the specified amount of columns and in the specified row
     */
    private void placePawns(int columns, int row, Color color) {
        for(int i = 0; i < columns; i++){
            addFigure(new Pawn(board[i][row], color));
        }
    }
}