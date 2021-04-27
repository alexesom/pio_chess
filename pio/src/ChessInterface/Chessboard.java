package ChessInterface;

import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.List;

public class Chessboard {
    public static Square[][] board;
    public static List<Piece> pieceList;
    public JPanel chessboardPanel;
    public JLayeredPane layer;
    public JLayeredPane capturedPiecesPanel1;
    public JLayeredPane capturedPiecesPanel2;
    private Adapter mouseAdapter;

    public Chessboard() {
        board = new Square[8][8];
        chessboardPanel = new JPanel();
        layer = new JLayeredPane();
        capturedPiecesPanel1 = new JLayeredPane();
        capturedPiecesPanel2 = new JLayeredPane();
        mouseAdapter = new Adapter(layer, capturedPiecesPanel1, capturedPiecesPanel2);

        createChessboardSquares();
        createCapturedPiecesPanel();
        placeChessboardPieces();
        addMouse();
    }

    public Chessboard(List<Piece> list) {
        pieceList = list;
        createChessboardSquares();
    }

    public static void movePieceInSquares(Square startSquare, Square destinationSquare) {
        int xPiece = destinationSquare.getXSquareCoordinate();
        int yPiece = destinationSquare.getYSquareCoordinate();
        destinationSquare.setSquarePiece(startSquare.getSquarePiece());
        destinationSquare.getSquarePiece().setxPieceCoordinate(xPiece);
        destinationSquare.getSquarePiece().setyPieceCoordinate(yPiece);
        startSquare.setSquarePiece(null);
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

    /*
    Place the pieces in their starting positions
     */
    private void placeChessboardPieces() {
        // Black pieces
        placePieces(7, Color.BLACK);
        placePawns(8, 6, Color.BLACK);

        // White pieces
        placePieces(0, Color.WHITE);
        placePawns(8, 1, Color.WHITE);
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
        for (int i = 0; i < columns; i++) {
            addFigure(new Pawn(board[i][row], color));
        }
    }

    public static class EnPassant {
        private static Pawn enPassantPawn;
        private static Square enPassantSquare;

        public static Pawn getEnPassantPawn() {
            return enPassantPawn;
        }

        public static Square getEnPassantSquare() {
            return enPassantSquare;
        }

        public static void setEnPassantPawn(Pawn enPassantPawnArg) {
            enPassantPawn = enPassantPawnArg;
        }

        public static void setEnPassantSquare(Square enPassantSquareArg) {
            enPassantSquare = enPassantSquareArg;
        }

        public static void makeNull() {
            enPassantPawn = null;
            enPassantSquare = null;
        }

        public static boolean isEmpty() {
            return enPassantPawn == null && enPassantSquare == null;
        }
    }
}